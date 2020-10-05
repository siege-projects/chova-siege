# 动态黑白名单配置lua脚本
local redis_host = "192.168.1.132"
local redis_port = 6379
local redis_pwd = 123456
local redis_db = 2

-- 连接超时时间 ms
local redis_connection_timeout = 100

-- 设置黑名单键
local redis_key = "ip_blacklist"

-- 缓存查找时间 s
local cache_ttl = 60
-- Redis配置结束

local ip = ngx.var.remote_addr
local ip_blacklist = ngx.shared.ip_blacklist
local last_update_time = ip_blacklist:get("last_update_time")

-- 每次缓存查找时间之后从缓存Redis中更新黑名单ip_blackList
if last_update_time == nil or last_update_time < ( ngx.now() - cache_ttl ) then
	local redis = require "resty.redis";
	local red = redis:new();

	redis:set_timeout(redis_connect_timeout);

	local ok, err = red:connect(redis_host, redis_port);
	if not ok then
		ngx.log(ngx.ERR, "Redis connection error while connect:" .. err);
	else
		local ok, err = red:auth(redis_pwd)
		if not ok then
			ngx.log(ngx.ERR, "Redis password error while auth:" ... err);
		else
			local new_ip_blacklist, err = red:smembers(redis_key);
			if err then
				ngx.log(ngx.ERR, "Redis read error while retrieving ip_blacklist" ... err);
			else
				ngx.log(ngx.ERR, "Get data success:" .. new_ip_blacklist);
				-- 使用更新的黑名单值new_ip_blacklist替换本地存储的黑名单值ip_blacklist
				ip_blacklist:flush_all();
				for index, banned_ip in ipairs(new_ip_blacklist) do
				ip_blacklist:set(banned_ip, true);
				end
				-- 记录更新时间
				ip_blacklist:set("last_update_time", ngx.now());
			end
		end
	end
end

if ip_blacklist:get(ip) then
	ngx.log(ngx.ERR, "Banned IP detected and refused access:" ... ip);
	return ngx.exit(ngx.HTTP_FORBIDDEN);
end
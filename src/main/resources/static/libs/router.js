(function() {
	window.Router = function() {
		var self = this;

		self.hashList = {}; /* 路由表 */
		self.index = null;
		self.key = '!';

		window.onhashchange = function() {
			self.reload();
		};
	};

	/**
	 * 添加路由,如果路由已經存在則會覆蓋
	 * @param addr: 地址
	 * @param callback: 回調函數，調用回調函數的時候同時也會傳入相應參數
	 */
	Router.prototype.add = function(addr, callback) {
		var self = this;

		self.hashList[addr] = callback;
	};

	/**
	 * 刪除路由
	 * @param addr: 地址
	 */
	Router.prototype.remove = function(addr) {
		var self = this;

		delete self.hashList[addr];
	};

	/**
	 * 設置主頁地址
	 * @param index: 主頁地址
	 */
	Router.prototype.setIndex = function(index) {
		var self = this;

		self.index = index;
	};


	/**
	 * 跳轉到指定地址
	 * @param addr: 地址值
	 */
	Router.prototype.go = function(addr) {
		var self = this;

		window.location.hash = '#' + self.key + addr;
	};

	/**
	 * 重載頁面
	 */
	Router.prototype.reload = function() {
		var self = this;

		var hash = window.location.hash.replace('#' + self.key, '');
		//var addr = hash.split('/')[0];
		var addr = hash;
		var cb = getCb(addr, self.hashList);
		if(cb != false) {
			var arr = hash.split('/');
			arr.shift();
			cb.apply(self, arr);
		}
		else {
			self.index && self.go(self.index);	
		}
	};

	/**
	 * 開始路由，實際上只是為了當直接訪問路由地址的時候能夠及時調用回調
	 */
	Router.prototype.start = function() {
		var self = this;

		self.reload();
	}

	/**
	 * 获取callback
	 * @return false or callback
	 */
	function getCb(addr, hashList) {
		for(var key in hashList) {
			if(key == addr) {
				return hashList[key]	
			}
		}
		return false;
	}
})();
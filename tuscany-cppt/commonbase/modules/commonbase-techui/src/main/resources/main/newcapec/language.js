require(
		[ "dojo/_base/config", "dojo/_base/kernel", "dojo/cookie" ],
		function(config, kernel, cookie) {

			if (navigator.cookieEnabled) {
				if (!cookie("locale")) {
					cookie("locale", capec.base.locale, {
						expires : capec.base.expires
					});
				}
				capec.base.locale = kernel.locale = config.locale = locale = cookie("locale");

				if (!cookie("theme")) {
					cookie("theme", capec.base.theme);
				}

				capec.base.theme = cookie("theme");

			} else {
				if (window.location.href.indexOf("?") > -1) {
					var __url_params_3 = window.location.href.substr(
							window.location.href.indexOf("?") + 1).split(/#/);

					var __array_params_3 = __url_params_3[0].split(/&/);

					for ( var __i_4 = 0, __j_4 = __array_params_3.length; __i_4 < __j_4; __i_4++) {
						var __split_5 = __array_params_3[__i_4].split("="), __key_5 = __split_5[0], __value_5 = (__split_5[1] || '')
								.replace(/[^\w]/g, "");

						switch (__key_5) {
						case "locale":
							capec.base.locale = kernel.locale = config.locale = locale = __value_5;
							break;
						case "theme":
							capec.base.theme = __value_5;
							break;
						}
					}
				}
			}
		});

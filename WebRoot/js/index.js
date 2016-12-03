
$(function() {
	$(".login_username").tooltip({
		content : "请输入您的用户名",
	});

	$(".login_password").tooltip({
		content : "请输入您的密码",
	});
	
	$(".login_username").focus(
			function() {
				$(".login_username_head").animate({
					/* opacity:'0.5', */
					width : '47px'
				}, 140, "swing");
				$(".login_user_img").show(150).css("z-index", "9001");
				setTimeout(function() {
					$(".login_username").css("padding-left", "47px").css("text-indent","8px").css("border","1px solid #2196F3").css("width","223px");
				}, 80);
			});
	$(".login_username").focus();
	
	$(".login_username").blur(
			function() {
				$(".login_username_head").animate({
					/* opacity:'0.5', */
					width : '0px'
				}, 50, "swing");
				$(".login_user_img").hide(50);
				$(".login_username").css("padding-left", "8px").css("border",
						"1px solid #DDDDDD").css("width","270px");
			});

	$(".login_password").focus(
			function() {
				$(".login_password_head").animate({
					/* opacity:'0.5', */
					width : '47px'
				}, 140, "swing");
				$(".login_password_img").show(150).css("z-index", "9001");
				setTimeout(function() {
					$(".login_password").css("padding-left", "47px").css("text-indent","8px").css("border","1px solid #2196F3").css("width","223px");
				},80);
				
			});

	$(".login_password").blur(
			function() {
				$(".login_password_head").animate({
					/* opacity:'0.5', */
					width : '0px'
				}, 50, "swing");
				$(".login_password_img").hide(50);
				$(".login_password").css("padding-left", "8px").css("border",
				"1px solid #DDDDDD").css("width","270px");
			});
	
	//回车时效果
	$(document).keyup(function(e){
		if(e.keyCode==13){
			e.preventDefault();
			if ($(".login_username").val().trim() == ""
					|| $(".login_password").val().trim() == "") {
				if ($(".login_username").val().trim() == "") {
					$(".login_username").css("border", "1px solid red");
					$(".login_user_img").hide();
					$(".login_username_head").css("width","0px");
					$(".login_username").css("padding-left", "0px").css("width","270px");
				}
				if ($(".login_password").val().trim() == "") {
					$(".login_password").css("border", "1px solid red");
					$(".login_password_img").hide();
					$(".login_password_head").css("width","0px");
					$(".login_password").css("padding-left", "0px").css("width","270px");
				}
			} else {
				$(".login_shuxian").css("display", "none");
				$(".login_submit").css("background", "#90EE90").css(
						"border-radius", "4px").val("请稍后...");
				$(".login_submit").animate({
					top : "0px",
					left : "0px",
					height : "370px",
					width : "350px"
				}, 400, "swing");
				$(".test").show(500).rotate({
					duration : 3000,
					angle : 0,
					animateTo : 3000
				});
				setTimeout(function() {
					$(".test").hide(50);
					$(".yes").fadeIn(150);
					setTimeout(function() {
						$(".login_submit").val("验证成功!");
						$("form").submit();
					}, 250);
				}, 1300);
			}
		}
	});

	// 点击登录时的动画效果
	$(".login_submit").click(
			function(e) {
				e.preventDefault();
				if ($(".login_username").val().trim() == ""
						|| $(".login_password").val().trim() == "") {
					if ($(".login_username").val().trim() == "") {
						$(".login_username").css("border", "1px solid red");
					}
					if ($(".login_password").val().trim() == "") {
						$(".login_password").css("border", "1px solid red");
					}
				} else {
					$(".login_shuxian").css("display", "none");
					$(".login_submit").css("background", "#90EE90").css(
							"border-radius", "4px").val("请稍后...");
					$(".login_submit").animate({
						top : "0px",
						left : "0px",
						height : "370px",
						width : "350px"
					}, 400, "swing");
					$(".test").show(500).rotate({
						duration : 3000,
						angle : 0,
						animateTo : 3000
					});
					setTimeout(function() {
						$(".test").hide(50);
						$(".yes").fadeIn(150);
						setTimeout(function() {
							$(".login_submit").val("验证成功!");
							$("form").submit();
						}, 250);
					}, 1300);
				}
			});

});
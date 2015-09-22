//简化
//由formValidate简化而来
//用法：页面引入本js，调用tsValidate.isEmpty(""); 返回值true
if(!window.tsValidate){
window.tsValidate = {
trim:function(str) 
{
	return str.replace(/(^\s*)|(\s*$)/g, ""); 
},
isEmpty:function(str){
	str = tsValidate.trim(str);
	if(str==""){ 
		return true;
	}else{
		return false;
	}
},
isSfz:function(code) { //验证身份证
    var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
    var tip = "";
    var pass= true;
    
    if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
       tip = "身份证号格式错误";
       pass = false;
   }  
    //alert(!city[code.substr(0,2)]);
   if(!city[code.substr(0,2)]){
        tip = "地址编码错误";
        pass = false;
    }
    else{
        //18位身份证需要验证最后一位校验位
        if(code.length == 18){
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
            //校验位
            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++)
            {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if(parity[sum % 11] != code[17]){
                tip = "校验位错误";
                pass =false;
            }
        }
    }    
    return pass|isCheckAICard(code);
},
isPhone:function(s){
	var patrn=/^1\d{10}$/;
	if (!patrn.exec(s)) {return false;}
	return true;
},
isMail:function(s){
	var patrn=/^[a-zA-Z0-9_\.]+@[a-zA-Z0-9-]{1,8}\.(com)|(cn)$/;
	if (!patrn.exec(s)) {return false;}
	return true;
},
isChinese:function(s){
	var patrn=/^[\u4e00-\u9fa5]{0,}$/;
	if (!patrn.exec(s)) {return false;}
	return true;
},
isNum:function(s){
	var patrn=/^[0-9]*$/;
	if (!patrn.exec(s)) {return false;}
	return true;
},
lenAllow:function(s,len){
	if(s==undefined){ return false;}
	if(s.length>len){ return false;}
	return true;
}
//tsvalidator end here
};
}
function isCheckAICard(value){
    var re= /^[0-9]{8}$/;
    return re.test(value) && !quanjiao(value);
}

function quanjiao(str){
 if (str.length>0){
  for (var i = str.length-1; i >= 0; i--){
   unicode=str.charCodeAt(i);
   if (unicode>65280 && unicode<65375){
    //alert("不能输入全角字符，请输入半角字符");
    return true;
   }
  }
 }
}
function errorShow(ele){ele.removeClass("invalid").addClass("invalid");}
jQuery(function($){ 
	//提交表单总验证,每添加一个验证方式都需要在这里加个trigger函数
	$(".form-valid").on("submit",function(){
		try{$(".input-valid").trigger("blur");}catch(e){}
		try{$(".sfz-valid").trigger("blur");}catch(e){}
		try{$(".zn-valid").trigger("blur");}catch(e){}
		try{$(".phone-valid").trigger("blur");}catch(e){}
		try{$(".mail-valid").trigger("blur");}catch(e){}
		try{$(".len60-valid").trigger("blur");}catch(e){}
		try{$(".len1000-valid").trigger("blur");}catch(e){}
		try{$(".len500-valid").trigger("blur");}catch(e){}
		var invalid = $(".invalid");
		if(invalid.length>0){
			return false;
		}
	});
	
	//必填项验证
	$(".input-valid").on("focus",function(){
		$(this).parent().children(".formtips").remove();
		$(this).removeClass("invalid");
		
	});
	$(".input-valid").on("blur",function(){
		$(this).parent().children(".formtips").remove();
		if(tsValidate.isEmpty($(this).val())){
			$(this).removeClass("invalid").addClass("invalid");
			$(this).parent().append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
		}else{
			$(this).removeClass("invalid");
			$(this).parent().append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
		}
	});
	//身份证验证
	$(".sfz-valid").on("focus",function(){
		$(this).removeClass("invalid");
		$(this).parent().children(".formtips").remove();
	});
	$(".sfz-valid").on("blur",function(){
		if(tsValidate.isEmpty($(this).val())){
			return;
		}
		$(this).parent().children(".formtips").remove();
		if(!tsValidate.isSfz($(this).val())){
			$(this).removeClass("invalid").addClass("invalid");
			$(this).parent().append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
		}else{
			$(this).removeClass("invalid");
			$(this).parent().append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
		}
		return true;
	});
	//电话验证
	$(".phone-valid").on("focus",function(){
		$(this).removeClass("invalid");
		$(this).parent().children(".formtips").remove();
	});
	$(".phone-valid").on("blur",function(){
		if(tsValidate.isEmpty($(this).val())){
			return;
		}
		$(this).parent().children(".formtips").remove();
		if(!tsValidate.isPhone($(this).val())){
			$(this).removeClass("invalid").addClass("invalid");
			$(this).parent().append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
		}else{
			$(this).removeClass("invalid");
			$(this).parent().append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
		}
	});
	//邮箱验证
	$(".mail-valid").on("focus",function(){
		$(this).removeClass("invalid");
		$(this).parent().children(".formtips").remove();
	});
	$(".mail-valid").on("blur",function(){
		if(tsValidate.isEmpty($(this).val())){
			return;
		}
		$(this).parent().children(".formtips").remove();
		if(!tsValidate.isMail($(this).val())){
			$(this).removeClass("invalid").addClass("invalid");
			$(this).parent().append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
		}else{
			$(this).removeClass("invalid");
			$(this).parent().append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
		}
	});
	//中文验证
	$(".zn-valid").on("focus",function(){
		$(this).removeClass("invalid");
		$(this).parent().children(".formtips").remove();
	});
	$(".zn-valid").on("blur",function(){
		if(tsValidate.isEmpty($(this).val())){
			return;
		}
	$(this).parent().children(".formtips").remove();
	if(!tsValidate.isChinese($(this).val())){
		$(this).removeClass("invalid").addClass("invalid");
		$(this).parent().append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
	}else{
		$(this).removeClass("invalid");
		$(this).parent().append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
	}
});
	//60字限制
	$(".len60-valid").on("focus",function(){
		$(this).removeClass("invalid");
		$(this).parent().children(".formtips").remove();
	});
	$(".len60-valid").on("blur",function(){
		if(tsValidate.isEmpty($(this).val())){
			return;
		}
		$(this).parent().children(".formtips").remove();
		if(!tsValidate.lenAllow($(this).val(),60)){
			$(this).removeClass("invalid").addClass("invalid");
			$(this).parent().append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
		}else{
			$(this).removeClass("invalid");
			$(this).parent().append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
		}
	});
	//1000字限制
	$(".len1000-valid").on("focus",function(){
		$(this).removeClass("invalid");
		$(this).parent().children(".formtips").remove();
	});
	$(".len1000-valid").on("blur",function(){
		if(tsValidate.isEmpty($(this).val())){
			return;
		}
		$(this).parent().children(".formtips").remove();
		if(!tsValidate.lenAllow($(this).val(),1000)){
			$(this).removeClass("invalid").addClass("invalid");
			$(this).parent().append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
		}else{
			$(this).removeClass("invalid");
			$(this).parent().append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
		}
	});
	//500字限制
	$(".len500-valid").on("focus",function(){
		$(this).removeClass("invalid");
		$(this).parent().children(".formtips").remove();
	});
	$(".len500-valid").on("blur",function(){
		if(tsValidate.isEmpty($(this).val())){
			return;
		}
		$(this).parent().children(".formtips").remove();
		if(!tsValidate.lenAllow($(this).val(),500)){
			$(this).removeClass("invalid").addClass("invalid");
			$(this).parent().append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
		}else{
			$(this).removeClass("invalid");
			$(this).parent().append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
		}
	});
	
});


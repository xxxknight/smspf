$(function(){ 
       //如果是必填的，则加红星标识. 
       $("form :input.required").each(function(){ 
           var $required = $("<span class='high'><strong> *</strong></span>"); //创建元素 
           $(this).parent().append($required); //然后将它追加到文档中 
       }); 
        //文本框失去焦点后 
       $('form :input').blur(function(){ 
            var $parent = $(this).parent(); 
            $parent.find(".formtips").remove(); 
            //验证邮件 
            if( $(this).is('#email') ){ 
            	if( this.value=="" || ( this.value!="" && !/^[a-zA-Z0-9_\.]+@[a-zA-Z0-9-]{1,8}\.(com)|(cn)$/.test(this.value) ) ){
                     $parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
               }else{ 
                     $parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
               } 
            } 
            //验证身份证号
            if( $(this).is('#idcard') ){ 
               if( this.value=="" || ( this.value!="" && !/^[1-9]([0-9]{14}|[0-9]{17})$/.test(this.value) ) ){ 
            	     $parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
               }else{ 
            	   $parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
               } 
            }
            //验证手机号码
            if( $(this).is('#mobile') ){ 
                if( this.value=="" || ( this.value!="" && !/^(1)[0-9]{10}$/.test(this.value) ) ){ 
                	  $parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
                }else{ 
                	  $parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
                } 
            }
          	//验证日期
            if( $(this).is('#date') ){ 
                if( this.value=="" || ( this.value!="" && !/^\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}$/.test(this.value) ) ){ 
                	  $parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
                }else{ 
                	$parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
                } 
            }
          	//非空
            if( $(this).is('#notempty') ){ 
                if( this.value=="" || ( this.value!="" && !/^\S+$/.test(this.value) ) ){ 
                	$parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
                }else{ 
                	  $parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
                } 
            }
            //验证整数
            if( $(this).is('#integer') ){ 
                if( this.value=="" || ( this.value!="" && !/^-?[1-9]\d*$/.test(this.value) ) ){ 
                	  $parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
                }else{ 
                	  $parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
                } 
            }
            //验证数字
            if( $(this).is('#num') ){ 
                if( this.value=="" || ( this.value!="" && !/^[0-9]*$/.test(this.value) ) ){ 
                	  $parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
                }else{ 
                	$parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
                } 
            }
            //验证中文
            if( $(this).is('#chinese') ){ 
                if( this.value=="" || ( this.value!="" && !/^[\u4e00-\u9fa5]{0,}$/.test(this.value) ) ){ 
                	  $parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
                }else{ 
                	  $parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
                } 
            }
            //验证电话号码(包括验证国内区号,国际区号,分机号)
            if( $(this).is('#tel') ){ 
                if( this.value=="" || ( this.value!="" && !/\d{3}-\d{8}|\d{4}-\d{7}/.test(this.value) ) ){ 
                	  $parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
                }else{ 
                	  $parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
                }
            }
            //验证字母
            if( $(this).is('#letter') ){ 
                if( this.value=="" || ( this.value!="" && !/^[A-Za-z]+$/.test(this.value) ) ){ 
                	  $parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
                }else{ 
                	  $parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
                }
            }
            //验证大写字母
            if( $(this).is('#letter_u') ){ 
                if( this.value=="" || ( this.value!="" && !/^[A-Z]+$/.test(this.value) ) ){ 
                	  $parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
                }else{ 
                	  $parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
                }
            }
            //验证小写字母
        	if( $(this).is('#letter_l') ){ 
                if( this.value=="" || ( this.value!="" && !/^[a-z]+$/.test(this.value) ) ){ 
                	  $parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
                }else{ 
                      //var okMsg = '输入正确.'; 
                      //$parent.append('<span class="formtips onSuccess">'+okMsg+'</span>');
                	  $parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
                }
            }
        	// 验证手机号码或者座机电话号码  add by zhanghan
        	if( $(this).is('#phonenumber') ) {
        		//验证电话号码(包括验证国内区号,国际区号,分机号)
                if( this.value=="" || ( this.value!="" && !/\d{3}-\d{8}|\d{4}-\d{7}/.test(this.value) 
                		&& !/^(1)[0-9]{10}$/.test(this.value) ) ){ 
            	      $parent.append("<span class='formtips onError'><image src='resources/images/delete.png' /></span>");
                } else{ 
	                  //var okMsg = '输入正确.'; 
	                  //$parent.append('<span class="formtips onSuccess">'+okMsg+'</span>');
	            	  $parent.append("<span class='formtips onSuccess'><image src='resources/images/yes.png' /></span>");
                } 
        	}
       }).keyup(function(){ 
          $(this).triggerHandler("blur"); 
       }).focus(function(){ 
           $(this).triggerHandler("blur"); 
       });//end blur 
       
       //提交，最终验证。 
       $('#send').click(function(){ 
  	        $("form :input.required").trigger('blur'); 
  	        var numError = $('form .onError').length; 
  	        if(numError){ 
  	            return false; 
  	        } 
  	        document.forms[1].submit();
       }); 
}); 
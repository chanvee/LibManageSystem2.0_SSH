/**
 * 
 */
$(document).ready(function() {
    
    $('#validateForm, #validateForm1').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
			
            name: {
                message: '用户名无效',
                validators: {
                    notEmpty: {
                        message: '用户名不能位空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '用户名必须大于6，小于30个字'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名只能由字母、数字、点和下划线组成'
                    }
                }
            },
            email: {
                validators: {
                	notEmpty: {
                        message: '邮箱不能位空'
                    },
                    emailAddress: {
                        message: '输入不是有效的电子邮件地址'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能位空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '密码必须大于6，小于30个字'
                    },
                    different: {
                        field: 'name',
                        message: '用户名和密码不能相同'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '两次密码不一致'
                    },
                }
            },
            gender: {
                validators: {
                    notEmpty: {
                        message: '请选择性别'
                    }
                }
            },
            level: {
                validators: {
                    notEmpty: {
                        message: '请选择等级'
                    }
                }
            }
        }
    });
    
    $('#resetBtn').click(function() {
        $('#validateForm').data('bootstrapValidator').resetForm(true);
    });
    
    $('#resetBtn1').click(function() {
        $('#validateForm1').data('bootstrapValidator').resetForm(true);
    });
    
    
});

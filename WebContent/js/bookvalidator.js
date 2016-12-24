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
			
            bookname: {
                message: '图书名无效',
                validators: {
                    notEmpty: {
                        message: '图书名不能位空'
                    }
                }
            },
            ISBN: {
                validators: {
                	notEmpty: {
                        message: 'ISBN不能位空'
                    }
                }
            },
            author: {
                validators: {
                    notEmpty: {
                        message: '作者不能位空'
                    }
                }
            },
            publisher: {
                validators: {
                    notEmpty: {
                        message: '出版社不能为空'
                    }
                }
            },
            status: {
                validators: {
                    notEmpty: {
                        message: '请选择状态'
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

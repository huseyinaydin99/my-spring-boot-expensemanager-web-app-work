$(function() {

    $.validator.addMethod('customEmail', function(value, element) {
        return this.optional(element) || /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(value);
    })

    const $registerForm = $('#registerForm');

    if($registerForm.length) {

        $registerForm.validate({

            rules: {
                name: {
                    required: true,
                    minlength: 3
                },
                email: {
                    required: true,
                    customEmail: true
                },
                password: {
                    required: true,
                    minlength: 5,
                    maxlength: 15
                },
                confirmpassword: {
                    required: true,
                    equalTo: '#password'
                }
            },
            messages: {
                name: {
                    required: 'İsim alanı boş geçilemez.',
                    minlength: 'İsim alanı minimum 3 karakter olmalıdır.'
                },
                email: {
                    required: 'Lütfen e-posta adresini giriniz.',
                    customEmail: 'Lütfen geçerli formatta bir e-posta adresi giriniz.'
                },
                password: {
                    required: 'Lütfen şifrenizi giriniz.',
                    minlength: 'Şifre uzunluğu en az 5 karakter olmalıdır.',
                    maxlength: 'Şifre uzunluğu en fazla 15 karakter olabilir.'
                },
                confirmpassword: {
                    required: 'Lütfen şifre tekrarını giriniz.',
                    equalTo: 'Şifre tekrarı girdiğiniz şifre ile aynı olmalıdır.'
                }
            },
            errorElement: 'em',
            errorPlacement: function(error, element) {
                error.addClass('help-block');
                error.insertAfter(element);
            }

        })
    }
})
let validNumber = new RegExp(/^(?!0)\d*\.?\d{0,2}$/);
let lastValid = $("#amount").val();
const validateNumber = function(elem) {
    if(validNumber.test(elem.value)) {
        lastValid = elem.value;
    }else {
        elem.value = lastValid;
    }
}
$(function(){
    $('#createdAt').datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        changeYear: true,
        maxDate: new Date()
    });


    const $expenseForm = $("#expenseform");

    if($expenseForm.length) {
        $expenseForm.validate({

            rules: {
                name: {
                    required: true,
                    minlength: 3
                },
                amount: {
                    required: true
                },
                dateString: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: 'Lütfen harcama adını giriniz.',
                    minlength: 'Harcama adı en az 3 karakter olabilir.'
                },
                amount: {
                    required: 'Lütfen miktarı giriniz.'
                },
                dateString: {
                    required: 'Lütfen harcama yaptığınız tarihi giriniz.'
                }
            }
        })
    }
})
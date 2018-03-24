$.validator.setDefaults({
    submitHandler: function () {
        alert("submit");
    },
    rules: {
        userID: {
            required: true,
            digits: true,
            rangelength: [11, 11]
        },
        password: "required",
        password1: "required",
        password2: {
            equalTo: "#password1"
        }
    },
    messages: {
        userID: {
            required: "用户ID不能为空",
            digits: "用户ID应该是数字",
            rangelength: "用户ID应该有11位"
        },
        password: "密码不能为空",
        password1: "密码不能为空",
        password2: {
            equalTo: "两次密码不一致"
        }
    },
    errorElement: "div",
    errorPlacement: function (error, element) {
        error.appendTo(element.parent());
    }
})
let login = {
    init() {
        let _this = this;
        $('#login-button').click(function () {
            let data = _this.getUserInfo();
            _this.loginAjax("/user/check","post", data);
        });
    },
    getUserInfo() {
        let username = $('#username').val();
        let password = $('#password').val();

        let data = {
            'username' : username,
            'password' : password
        }
        return data;
    },
    loginAjax: function (url, method, data) {
        $.ajax({
            url : url,
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            method: method
        }).done(function (data) {
            window.location.href="/game"
        }).fail(function () {
            alert('로그인 실패');
        });
    }
};
login.init();
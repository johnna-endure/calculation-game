let register = {
    init: function () {
        let _this = this;
        $("#register-button").click(function () {
            _this.registerUser();
        });
    },

    registerUser: function() {
        let data = this.getUserInfo();
        if(data.username.length > 0 && data.password.length > 0){
            this.ajax("/user", 'post', data);
            return;
        }
        alert('빈 칸이 있습니다.');
    },

    getUserInfo: function(){
        let username = $('#username').val();
        let password = $('#password').val();
        return {
            'username' : username,
            'password' : password
        };
    },

    ajax: function (url, method, data) {
        $.ajax({
            url : url,
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            method: method
        }).done(function (id) {
            window.location.href='/game/'+id;
        }).fail(function () {
            alert('실패');
        });
    }
}
register.init();
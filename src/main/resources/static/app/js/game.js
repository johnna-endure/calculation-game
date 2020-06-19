let game = {
    init(){
        let _this = this;
        _this.getNewProblem();
    },
    getNewProblem() {
        $.ajax({
            url: '/problem',
            contentType: 'application/json',
            method: 'post',
            success(data) {
                $('#problem').val(data.problem);
            },
            error() {
                alert('실패');
            }
        });
    },
}

game.init();
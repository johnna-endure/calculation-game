let game = {
    init(){
        let _this = this;
        $('#submit-answer-button').click(function () {
            _this.sendProblem();
            _this.getExpression();
        });
        _this.getId();
    },
    getId() {
        let url = window.location.href;
        let matchArray = url.match(/\/(\d+)$/);
        return matchArray[1];
    },
    sendProblem() {
        let _this = this;
        let expression = $('#problem-view').val();
        let answer = $('#answer').val();
        let id = this.getId();
        let data = {
            'id': id,
            'expression' : expression,
            'answer' : answer
        };
        $.ajax({
            url: '/game',
            contentType: 'application/json',
            method: 'post',
            data: JSON.stringify(data),
            dataType: 'json',
            success(response) {
                if(response.solved) {
                    alert('정답입니다.');
                    $('#score-view').text(response.score);
                }else {
                    alert('아쉽네요.');
                }
                _this.getGameCardList();
            },
            error() {
                console.log('sendProblem() ajax 요청 실패');
            }
        });
    },
    getExpression() {
        $.ajax({
            url: '/game',
            contentType: 'application/json',
            method: 'get',
            success(expression) {
                $('#problem-view').val(expression);
            },
            error() {
                console.log('getExpression() ajax 요청 실패');
            }
        });
    },
    getGameCardList() {
        let id = this.getId();
        $.ajax({
            url: '/games/'+id,
            contentType: 'application/json',
            method: 'get',
            success(cardList) {
                //문제, 답, 정답 여부, 점수
                console.log(cardList);
                let body_html = '';
                for (let i = 0; i < cardList.length; i++) {
                    body_html += `<tr>`;
                    let card = cardList[i];
                    body_html += `<td>${card.problem.expression}</td>`;
                    body_html +=`<td>${card.problem.answer}</td>`;
                    body_html += `<td>${card.solved}</td>`;
                    body_html +='</tr>';
                }
                $('#gamecard-list-view').html(body_html);
                // document.getElementById('gamecard-list-view').innerHTML = body_html;
                // console.log(body_html);
            },
            error() {
                console.log('getExpression() ajax 요청 실패');
            }
        });
    }
}

game.init();
/** like ajax */
$( document ).ready(function() {
    $("#like-btn").click(function (){
        const reviewId = $('#like-review-id').val();
        $.ajax({
            type: 'POST',
            data: {reviewId: reviewId},
            url: 'like.do',
            success: function (result){
                let likes_num = parseInt($('#likes-num').innerHTML, 10);
                likes_num++;
                $('#likes-num').innerHTML = likes_num.toString(10);
                $('#likes-num').innerHTML = result;
            },
            error: function (){
                alert("error");
            }
        });
    });
});
/** like ajax */
$(document).ready(function (){
    $('.like-form').submit(function (e){
        e.preventDefault();
        const like_form = $(this);
        const review_id = like_form.find('.like-input-id')[0].value;
        const url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data: {
                'reviewId': review_id,
            },
            success: function(result){
                like_form.find('div')[0].innerText = result.likes;
                $('#dislike-form-' + review_id).find('div')[0].innerText = result.dislikes;
            },
            error: function(data){
                console.log('error', data);
            }
        })
    })
})
/** dislike ajax */
$(document).ready(function (){
    $('.dislike-form').submit(function (e){
        e.preventDefault();
        const dislike_form = $(this);
        const review_id = dislike_form.find('.dislike-input-id')[0].value;
        const url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data: {
                'reviewId': review_id,
            },
            success: function(result){
                $('#like-form-' + review_id).find('div')[0].innerText = result.likes;
                dislike_form.find('div')[0].innerText = result.dislikes;
            },
            error: function(data){
                console.log('error', data);
            }
        })
    })
})
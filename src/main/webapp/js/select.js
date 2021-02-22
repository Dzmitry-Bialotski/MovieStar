/** script that selects option from input hidden */
const genre = document.getElementById('genre').value;
const select_genre = document.querySelector('#select-genre').getElementsByTagName('option');
for (let i = 0; i < select_genre.length; i++) {
    if (select_genre[i].value === genre) select_genre[i].selected = true;
}

const movie_type = document.getElementById('movie_type').value;
const select_movie_type = document.querySelector('#select-movie_type').getElementsByTagName('option');
for (let i = 0; i < select_movie_type.length; i++) {
    if (select_movie_type[i].value === movie_type) select_movie_type[i].selected = true;
}

const age_category = document.getElementById('age_category').value;
const select_age_category = document.querySelector('#select-age_category').getElementsByTagName('option');
for (let i = 0; i < select_age_category.length; i++) {
    if (select_age_category[i].value === age_category) select_age_category[i].selected = true;
}

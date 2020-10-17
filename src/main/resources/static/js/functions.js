function addMeme(form) {
    form.submit(function (event) {
        event.preventDefault();
        const meme = $("#meme").val();
        const newMeme = {
            meme: meme,
        };

        $.ajax({
            url: "/api/add-meme",
            type: "POST",
            contentType: "application/JSON",
            data: JSON.stringify(newMeme),
            success: function(data) {
                console.log("Success");
                $("#meme-table tbody").prepend("<tr><td>" + data.meme + "</td></tr>")
            },
            error: function(data) {
                console.log("Error in server.")
            }
        });
        form[0].reset();
    })
}


function getAllMemes() {
    $.ajax({
        url: "/api/get-all-memes",
        type: "GET",
        contentType: "application/JSON",
        success: function(data) {
            $.each(data.memes, function (index, value) {
                $("#meme-table tbody").prepend("<tr><td>" + value.meme + "</td></tr>")
            })
        },
        error: function(data) {
            console.log("Error in server.")
        }
    })
}
function addToCart(id , name, price){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/addToCart",
        data: JSON.stringify(id)
    });
}

function addToCart(id , name, price){
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/addToCart",
        async:true,
        data: JSON.stringify(id),
        success:function(data){
            console.log(data);
        }
    });
    


}

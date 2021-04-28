function removeUser(obj){
    var index =$(".u_del").index(obj);
    var id = $(".u_id").eq(index).val();
    if (confirm("真的要把他踢了么？")){
        window.location.href="/user/deleteUser/"+id;
    }
}
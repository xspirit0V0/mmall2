function removeUser(obj){
    var index =$(".u_del").index(obj);
    var id = $(".u_id").eq(index).val();
    if (confirm("真的要把他踢了么？")){
        window.location.href="/user/deleteUser/"+id;
    }
}
function updateInformation(obj){
    var index = $(".u_upi").index(obj);
    var id = $(".u_id").eq(index).val();
    if (confirm("是否确认修改?")){
        $.ajax({
           type:"POST",
            url:"/user/updateInformation/"+id
        });
    }
}

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function toggle(){
    
    var user_types = document.getElementsByName('userType');
    var user_type;
     
    for(var i = 0; i< user_types.length; i++){
        if(user_types[i].checked){
            user_type = user_types[i].value;
        }
    }
    
    
    if(user_type === 'serviceProvider'){
        
        document.getElementById('selUserSkills').style.display  = 'block';
    }else{
        
        document.getElementById('selUserSkills').style.display  = 'none';
    }
    
    
}
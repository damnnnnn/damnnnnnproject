<html xml:lang="zh-CN">  
<head>    
 <title>test!</title>  
 
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
</head>  
<body>  
    <div>   
        String:${strs}<br/>     
        <hr/>  
    </div>  
      <div>   
       ${arrdatas[0]} ,  ${arrdatas[1]} , ${arrdatas[2]}
        <hr/>  
    </div> 
    
     <div>   
     <ul>
      <#list setdatas  as  setvalue>
          ${setvalue}
      </#list>
      </ul>
        <hr/>  
    </div> 
     <div>   
     <ul>
      <#list listsdatas  as  listval>
          ${listval}
      </#list>
      </ul>
        <hr/>  
    </div> 


      <div>   
     <table  border="1">
     
      <#list userdatas  as  users>
       <tr>
        <td>${users.id}</td>
        <td>${users.username}</td>
	<td>${users.userpwd}</td>
      </tr>
      </#list>
      </table>
        <hr/>  
    </div> 

      <div>   
     ${namemaps.key1}
        <hr/>  
    </div> 

     <div>   
     ${cnames[0]}, ${cnames[1]},${cnames[2]}
        <hr/>  
    </div> 
</body>     
</html>    
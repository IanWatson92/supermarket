<h1>${message}</h1>
<h1>The above text is set using a FreeMarkerEngine</h1>
<#list items?keys as item>
    ${item} and ${items[item]['name']}
</#list>
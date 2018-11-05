<#include "/macro/html-lichkin-simple.ftl"/> 
<@html css=true js=false i18nJs=false;section> 
<#if section="body-content">
  <div id="successDiv" class="successDiv">
  <#if openAuthResult == 0>
    <div class="successTips">授权成功！</div>
    <img src="${ctx }/res/img/OAuthSuccess.png" width="200px" height="200px"></img>
  <#else>
    <div class="successTips">授权失败！</div>
    <img src="${ctx }/res/img/OAuthFail.png" width="200px" height="200px"></img>
    <div id="openAuthBtn" class="openAuthBtn"><a href="${openAuthUrl!}" target="_blank">点击重新授权</a></div>
  </#if>
  </div>
</#if>
</@html>

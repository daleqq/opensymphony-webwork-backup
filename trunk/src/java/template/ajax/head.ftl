<#include "/${parameters.templateDir}/xhtml/head.ftl" />
<script language="JavaScript" type="text/javascript">
    // Dojo configuration
    djConfig = {
        baseRelativePath: "<@ww.url includeParams='none' value='/webwork/dojo/'/>",
        isDebug: false,
        debugAtAllCosts: true // not needed, but allows the Venkman debugger to work with the includes
    };
</script>
<script language="JavaScript" type="text/javascript"
        src="<@ww.url includeParams='none' value='/webwork/dojo/dojo.js' />"></script>
<script language="JavaScript" type="text/javascript"
        src="<@ww.url includeParams='none' value='/webwork/CommonFunctions.js' />"></script>
<script language="JavaScript" type="text/javascript"
        src="<@ww.url includeParams='none' value='/webwork/ajax/dojoRequire.js' />"></script>
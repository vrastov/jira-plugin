package ru.cbr.jira.plugins.jira.webwork;

import com.atlassian.jira.security.Permissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.web.action.JiraWebActionSupport;

public class CastomFieldConfigurationAction extends JiraWebActionSupport
{
    private static final Logger log = LoggerFactory.getLogger(CastomFieldConfigurationAction.class);

    @Override
    public String execute() throws Exception {

        if (!isHasPermission(Permissions.ADMINISTER)) {
            return "securitybreach";
        }

        String save = request.getParameter("Save");
//        if (save != null && save.equals("Save")) {
//            setReturnUrl("/secure/admin/ConfigureCustomField!default.jspa?customFieldId="
//                    + getFieldConfig().getCustomField().getIdAsLong().toString());
//            return getRedirect("not used");
//        }

        return super.execute(); //returns SUCCESS
    }
}

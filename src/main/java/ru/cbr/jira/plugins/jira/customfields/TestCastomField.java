package ru.cbr.jira.plugins.jira.customfields;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TestCastomField extends AbstractSingleFieldType<Dto> {
    private static final Logger log = LoggerFactory.getLogger(TestCastomField.class);
    private Parser parser = new Parser();

    public TestCastomField() {
        super(getComponent(CustomFieldValuePersister.class), getComponent(GenericConfigManager.class));
    }

    private static <T> T getComponent(Class<T> tClass) {
        return ComponentAccessor.getComponent(tClass);
    }

    @Override
    protected PersistenceFieldType getDatabaseType() {
        return PersistenceFieldType.TYPE_UNLIMITED_TEXT;
    }

    @Override
    protected Object getDbValueFromObject(final Dto customFieldObject) {
        return getStringFromSingularObject(customFieldObject);
    }

    @Override
    protected Dto getObjectFromDbValue(final Object databaseValue)
            throws FieldValidationException {
        return getSingularObjectFromString((String) databaseValue);
    }

    @Override
    public String getStringFromSingularObject(final Dto singularObject) {
        if (singularObject == null) {
            return "";
        }
        // format
        return singularObject.getUrl() + " " + singularObject.getTitle();
    }

    @Override
    public Dto getSingularObjectFromString(final String string)
            throws FieldValidationException {
        return parser.parse(string);
    }

    @Override
    public Map<String, Object> getVelocityParameters(final Issue issue,
                                                     final CustomField field,
                                                     final FieldLayoutItem fieldLayoutItem) {
        final Map<String, Object> map = super.getVelocityParameters(issue, field, fieldLayoutItem);
        map.put("parser",parser);
        return map;
    }

}

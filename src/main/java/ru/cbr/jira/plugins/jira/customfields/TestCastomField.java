package ru.cbr.jira.plugins.jira.customfields;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.config.FieldConfigItemType;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.List;
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

    @Nonnull
    @Override
    protected PersistenceFieldType getDatabaseType() {
        return PersistenceFieldType.TYPE_UNLIMITED_TEXT;
    }

    @Override
    protected Object getDbValueFromObject(final Dto customFieldObject) {
        return getStringFromSingularObject(customFieldObject);
    }

    @Override
    protected Dto getObjectFromDbValue(@Nonnull final Object databaseValue)
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

    @Nonnull
    @Override
    public Map<String, Object> getVelocityParameters(final Issue issue,
                                                     final CustomField field,
                                                     final FieldLayoutItem fieldLayoutItem) {
        final Map<String, Object> map = super.getVelocityParameters(issue, field, fieldLayoutItem);
        map.put("parser",parser);

        // This method is also called to get the default value, in
        // which case issue is null so we can't use it to add currencyLocale
        if (issue == null) {
            return map;
        }
        FieldConfig fieldConfig = field.getRelevantConfig(issue);
        // Get the stored configuration choice
//        map.put("currencyLocale", DAO.getCurrentLocale(fieldConfig));
        map.put("currencyLocale", "currencyLocale");

        return map;
    }

    @Nonnull
    public List<FieldConfigItemType> getConfigurationItemTypes() {
        final List<FieldConfigItemType> configurationItemTypes =
                super.getConfigurationItemTypes();
        configurationItemTypes.add(new CustomFieldConfigItem());
        return configurationItemTypes;
    }

}

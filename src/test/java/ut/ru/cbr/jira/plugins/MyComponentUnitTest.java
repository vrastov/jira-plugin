package ut.ru.cbr.jira.plugins;

import org.junit.Test;
import ru.cbr.jira.plugins.api.MyPluginComponent;
import ru.cbr.jira.plugins.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}
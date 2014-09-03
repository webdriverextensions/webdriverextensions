package com.github.webdriverextensions.dataloader;

import static com.github.webdriverextensions.Bot.assertThat;
import com.github.webdriverextensions.datalaoder.Data;
import static com.github.webdriverextensions.datalaoder.DataContext.getData;
import com.github.webdriverextensions.datalaoder.DataLoader;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.Rule;
import org.junit.Test;

@Data("class-data.properties")
public class DataLoaderTest {

    @Rule
    public DataLoader data = new DataLoader(this);

    @Test
    @Data("method-data.properties")
    public void test() {
        assertThat(getData("data-key"), is(equalTo("data-value")));
        assertThat(getData("class-data-key"), is(equalTo("class-data-value")));
        assertThat(getData("method-data-key"), is(equalTo("method-data-value")));
    }

}

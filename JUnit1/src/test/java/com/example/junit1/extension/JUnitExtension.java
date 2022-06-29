package com.example.junit1.extension;

import com.example.junit1.Tags.SlowTest;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class JUnitExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private final long THRESHOLD;

    public JUnitExtension(long threshold) {
        THRESHOLD = threshold;
    }


    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = getStore(context);
        store.put("START_TIME", System.currentTimeMillis());
    }


    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        String testMethodName = context.getRequiredTestMethod().getName();
        ExtensionContext.Store store = getStore(context);

        long start_time = store.remove("START_TIME", long.class);
        long duration = System.currentTimeMillis() - start_time;

        SlowTest slowTestAnnotation = context.getRequiredTestMethod().getAnnotation(SlowTest.class);
        if(duration > THRESHOLD & slowTestAnnotation == null) {
            System.out.printf("please consider mark method [%s] with @SlowTest. \n", testMethodName);
        }
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        return context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
    }
}

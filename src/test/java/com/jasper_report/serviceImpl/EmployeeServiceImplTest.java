package com.jasper_report.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jasper_report.dto.Entity;
import com.jasper_report.dto.MultiTableColumnsParams;
import com.jasper_report.exception.EmployeeException;
import com.jasper_report.mapper.EntityMapper;
import com.jasper_report.mapper.EntityPropertyMapper;
import com.jasper_report.mapper.MultiTableColumnsResultMapper;
import com.jasper_report.mapper.StyleBuilderMapper;
import com.jasper_report.repository.CommonRepository;
import com.jasper_report.repository.StyleBuilderDuplicateRepository;
import com.jasper_report.repository.StyleBuilderRepository;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeServiceImpl.class, CommonRepository.class, CommonService.class,
        EntityMapper.class, EntityPropertyMapper.class, JasperReportServiceImpl.class, MultiTableColumnsResultMapper.class,
        StyleBuilderDuplicateRepository.class, StyleBuilderMapper.class, StyleBuilderRepository.class})
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @MockBean
    private EntityManager entityManager;

    /**
     * Method under test: {@link EmployeeServiceImpl#getTablesList(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTablesList() {
        // TODO: Complete this test.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.LibraryLinkageException: java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.ClassNotFoundException: org.mockito.Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   See https://diff.blue/R026 to resolve this issue.

        employeeServiceImpl.getTablesList("Schema Name");
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getTableColumnsAndChildTables(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTableColumnsAndChildTables() throws SQLException {
        // TODO: Complete this test.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.LibraryLinkageException: java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.ClassNotFoundException: org.mockito.Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   See https://diff.blue/R026 to resolve this issue.

        employeeServiceImpl.getTableColumnsAndChildTables("Schema Name", "Table Name");
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getJasperReport(boolean, MultiTableColumnsParams)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetJasperReport() throws FileNotFoundException, ClassNotFoundException, JRException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.LibraryLinkageException: java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.ClassNotFoundException: org.mockito.Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Collection.isEmpty()" because "that" is null
        //       at com.jasper_report.serviceImpl.EmployeeServiceImpl.getJasperReport(EmployeeServiceImpl.java:173)
        //   See https://diff.blue/R013 to resolve this issue.

        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
        employeeServiceImpl.getJasperReport(true, new MultiTableColumnsParams());
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getJasperReport(boolean, MultiTableColumnsParams)}
     */
    @Test
    void testGetJasperReport2() throws FileNotFoundException, ClassNotFoundException, JRException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.LibraryLinkageException: java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.ClassNotFoundException: org.mockito.Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   See https://diff.blue/R026 to resolve this issue.

        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

        MultiTableColumnsParams multiTableColumnsParams = new MultiTableColumnsParams();
        multiTableColumnsParams.setEntityList(new ArrayList<>());
        assertThrows(EmployeeException.class, () -> employeeServiceImpl.getJasperReport(true, multiTableColumnsParams));
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getJasperReport(boolean, MultiTableColumnsParams)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetJasperReport3() throws FileNotFoundException, ClassNotFoundException, JRException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.LibraryLinkageException: java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.ClassNotFoundException: org.mockito.Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.jasper_report.dto.MultiTableColumnsParams.getEntityList()" because "multiTableColumnsParams" is null
        //       at com.jasper_report.serviceImpl.EmployeeServiceImpl.getJasperReport(EmployeeServiceImpl.java:173)
        //   See https://diff.blue/R013 to resolve this issue.

        (new EmployeeServiceImpl()).getJasperReport(true, null);
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getJasperReport(boolean, MultiTableColumnsParams)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetJasperReport4() throws FileNotFoundException, ClassNotFoundException, JRException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.LibraryLinkageException: java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.ClassNotFoundException: org.mockito.Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.jasper_report.serviceImpl.CommonService.convertToDatabaseName(String)" because "this.commonService" is null
        //       at com.jasper_report.serviceImpl.EmployeeServiceImpl.getJasperReport(EmployeeServiceImpl.java:182)
        //   See https://diff.blue/R013 to resolve this issue.

        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

        ArrayList<Entity> entityList = new ArrayList<>();
        entityList.add(new Entity());

        MultiTableColumnsParams multiTableColumnsParams = new MultiTableColumnsParams();
        multiTableColumnsParams.setEntityList(entityList);
        employeeServiceImpl.getJasperReport(true, multiTableColumnsParams);
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getJasperReport(boolean, MultiTableColumnsParams)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetJasperReport5() throws FileNotFoundException, ClassNotFoundException, JRException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.LibraryLinkageException: java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.ClassNotFoundException: org.mockito.Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.jasper_report.dto.Entity.getTableName()" because the return value of "com.diffblue.cover.agent.readwrite.RuntimeWrappers.list$get(java.util.List, int)" is null
        //       at com.jasper_report.serviceImpl.EmployeeServiceImpl.getJasperReport(EmployeeServiceImpl.java:182)
        //   See https://diff.blue/R013 to resolve this issue.

        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

        ArrayList<Entity> entityList = new ArrayList<>();
        entityList.add(null);

        MultiTableColumnsParams multiTableColumnsParams = new MultiTableColumnsParams();
        multiTableColumnsParams.setEntityList(entityList);
        employeeServiceImpl.getJasperReport(true, multiTableColumnsParams);
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getSchemas()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetSchemas() {
        // TODO: Complete this test.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.LibraryLinkageException: java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.ClassNotFoundException: org.mockito.Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   See https://diff.blue/R026 to resolve this issue.

        employeeServiceImpl.getSchemas();
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getMultipleTableColumnsAndChildTables(String, List)}
     */
    @Test
    void testGetMultipleTableColumnsAndChildTables() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.LibraryLinkageException: java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.ClassNotFoundException: org.mockito.Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   See https://diff.blue/R026 to resolve this issue.

        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
        assertThrows(EmployeeException.class,
                () -> employeeServiceImpl.getMultipleTableColumnsAndChildTables("Schema Name", new ArrayList<>()));
    }

    /**
     * Method under test: {@link EmployeeServiceImpl#getMultipleTableColumnsAndChildTables(String, List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMultipleTableColumnsAndChildTables2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.LibraryLinkageException: java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.NoClassDefFoundError: org/mockito/Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   java.lang.ClassNotFoundException: org.mockito.Answers
        //       at java.lang.Class.getDeclaredMethods0(Native Method)
        //       at java.lang.Class.privateGetDeclaredMethods(Class.java:3402)
        //       at java.lang.Class.getDeclaredMethods(Class.java:2504)
        //       at java.security.AccessController.doPrivileged(AccessController.java:318)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   See https://diff.blue/R026 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.jasper_report.serviceImpl.CommonService.convertToDatabaseName(String)" because "this.commonService" is null
        //       at com.jasper_report.serviceImpl.EmployeeServiceImpl.getTableColumnsAndChildTables(EmployeeServiceImpl.java:118)
        //       at com.jasper_report.serviceImpl.EmployeeServiceImpl.lambda$getMultipleTableColumnsAndChildTables$4(EmployeeServiceImpl.java:369)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:762)
        //       at com.jasper_report.serviceImpl.EmployeeServiceImpl.getMultipleTableColumnsAndChildTables(EmployeeServiceImpl.java:366)
        //   See https://diff.blue/R013 to resolve this issue.

        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("TableNames List is empty, pls add some table names");
        employeeServiceImpl.getMultipleTableColumnsAndChildTables("Schema Name", stringList);
    }
}


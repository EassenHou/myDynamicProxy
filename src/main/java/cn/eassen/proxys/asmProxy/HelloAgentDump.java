package cn.eassen.proxys.asmProxy;

import cn.eassen.mydynamicproxy.service.StudentService;
import org.objectweb.asm.*;


/**
 * ASM动态代理
 * @author eassen
 */
public class HelloAgentDump implements Opcodes {

    // 生成一个class的字节数组
    public static byte[] dump() throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        // 定义class版本1.8，访问权限，类名，继承类，实现接口等信息
        classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "cn/eassen/proxys/asmProxy/HelloASMProxy", null, "java/lang/Object", new String[]{"cn/eassen/mydynamicproxy/service/StudentService"});

        classWriter.visitSource("getStudentserAgent.java", null);

        // 定义私有属性
        {
            fieldVisitor = classWriter.visitField(ACC_PRIVATE, "delegate", "Lcn/eassen/mydynamicproxy/service/StudentService;", null, null);
            fieldVisitor.visitEnd();
        }
        {
            // 定义构造器
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "(Lcn/eassen/mydynamicproxy/service/StudentService;)V", null, null);
            methodVisitor.visitParameter("delegate", 0);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(10, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(11, label1);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitFieldInsn(PUTFIELD, "cn/eassen/proxys/asmProxy/HelloASMProxy", "delegate", "Lcn/eassen/mydynamicproxy/service/StudentService;");
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(12, label2);
            methodVisitor.visitInsn(RETURN);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("this", "Lcn/eassen/proxys/asmProxy/HelloASMProxy;", null, label0, label3, 0);
            methodVisitor.visitLocalVariable("delegate", "Lcn/eassen/mydynamicproxy/service/StudentService;", null, label0, label3, 1);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }
        {
            // 定义方法getStudents
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "getStudents", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(16, label0);
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitLdcInsn("before getStudents...");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(17, label1);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "cn/eassen/proxys/asmProxy/HelloASMProxy", "delegate", "Lcn/eassen/mydynamicproxy/service/StudentService;");
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "cn/eassen/mydynamicproxy/service/StudentService", "getStudents", "()V", true);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(18, label2);
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitLdcInsn("after getStudents...");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(19, label3);
            methodVisitor.visitInsn(RETURN);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLocalVariable("this", "Lcn/eassen/proxys/asmProxy/HelloASMProxy;", null, label0, label4, 0);
            methodVisitor.visitMaxs(2, 1);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }

    // 将字节数组加载为class
    public static StudentService newProxyInstance(StudentService delegate) throws Exception {
        String className = "cn.eassen.proxys.asmProxy.HelloASMProxy";
        byte[] classData = dump();
        Class<?> aClass = new MyClassLoader().defineClassForName(className, classData);
        return (StudentService) aClass.getDeclaredConstructor(StudentService.class).newInstance(delegate);
    }
}


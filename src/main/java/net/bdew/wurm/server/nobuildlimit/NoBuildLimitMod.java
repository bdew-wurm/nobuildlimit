package net.bdew.wurm.server.nobuildlimit;

import javassist.ClassPool;
import javassist.CtClass;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.interfaces.Initable;
import org.gotti.wurmunlimited.modloader.interfaces.PreInitable;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NoBuildLimitMod implements WurmServerMod, Initable, PreInitable {
    private static final Logger logger = Logger.getLogger("NoBuildLimitMod");

    public static void logException(String msg, Throwable e) {
        if (logger != null)
            logger.log(Level.SEVERE, msg, e);
    }

    @Override
    public void init() {
    }

    @Override
    public void preInit() {
        try {
            ClassPool classPool = HookManager.getInstance().getClassPool();
            CtClass ctMethodsStructure = classPool.getCtClass("com.wurmonline.server.behaviours.MethodsStructure");

            ctMethodsStructure.getMethod("hasEnoughSkillToExpandStructure", "(Lcom/wurmonline/server/creatures/Creature;IILcom/wurmonline/server/structures/Structure;)Z")
                    .setBody("return true;");

            ctMethodsStructure.getMethod("hasEnoughSkillToContractStructure", "(Lcom/wurmonline/server/creatures/Creature;IILcom/wurmonline/server/structures/Structure;)Z")
                    .setBody("return true;");

        } catch (Throwable e) {
            throw new RuntimeException("Error in preinit", e);
        }
    }
}

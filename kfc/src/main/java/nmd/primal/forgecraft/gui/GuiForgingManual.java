package nmd.primal.forgecraft.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

/**
 * Created by mminaie on 2/4/17.
 */
public class GuiForgingManual extends GuiScreen {

    GuiButton a;
    GuiButton b;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {

        this.buttonList.add(this.a = new GuiButton(0, this.width / 2 - 100, this.height / 2 - 24, "This is button a"));
        this.buttonList.add(this.b = new GuiButton(1, this.width / 2 - 100, this.height / 2 + 4, "This is button b"));

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == this.a) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button == this.b){
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
    }

}

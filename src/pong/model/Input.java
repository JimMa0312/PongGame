package pong.model;

import java.util.BitSet;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Input {
	
	private BitSet keyboardBitSet=new BitSet();
	
	private KeyCode upKey=KeyCode.UP;
	private KeyCode downKey=KeyCode.DOWN;
	private KeyCode leftKey=KeyCode.LEFT;
	private KeyCode rightKey=KeyCode.RIGHT;
	private KeyCode primaryWeaponKey=KeyCode.PAUSE;
	private KeyCode seconderyWeaponKey=KeyCode.ENTER;
	
	private Scene scene;

	public Input(Scene scene) {
		this.scene=scene;
	}
	
	public void addListeners(){
		scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
		scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
	}
	
	public void removeListeners(){
		scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
		scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
	}
	
	/*
	 * "输入键为 压入"
	 */
	
	public EventHandler<KeyEvent> keyPressedEventHandler=new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			//登记输入的键位
			keyboardBitSet.set(event.getCode().ordinal(),true);
		}
	};
	
	/*
	 * "输入键为释放键"
	 */
	public EventHandler<KeyEvent> keyReleasedEventHandler=new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			// 登记键位
			keyboardBitSet.set(event.getCode().ordinal(),false);
		}
	};
	
	
	public boolean isMoveUp() {
		return keyboardBitSet.get(upKey.ordinal())
				&& !keyboardBitSet.get(downKey.ordinal());
	}
	
	public boolean isMoveDown(){
		return keyboardBitSet.get(downKey.ordinal())
				&& !keyboardBitSet.get(upKey.ordinal());
	}
	
	public boolean isMoveLeft(){
		return keyboardBitSet.get(leftKey.ordinal())
				&& !keyboardBitSet.get(rightKey.ordinal());
	}
	
	public boolean isMoveRight(){
		return keyboardBitSet.get(rightKey.ordinal())
				&& !keyboardBitSet.get(leftKey.ordinal());
	}
	
	public boolean isFirePrimaryWeapon(){
		return keyboardBitSet.get(primaryWeaponKey.ordinal());
	}
	
	public boolean isFireSecondaryWeapon(){
		return keyboardBitSet.get(seconderyWeaponKey.ordinal());
	}
}

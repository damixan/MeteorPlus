package nekiplay.meteorplus.features.modules.combat.velocity;

public enum VelocityModes {
	Grim_Cancel,
	Grim_Cancel_v2;

	@Override
	public String toString() {
		return super.toString().replace('_', ' ');
	}
}

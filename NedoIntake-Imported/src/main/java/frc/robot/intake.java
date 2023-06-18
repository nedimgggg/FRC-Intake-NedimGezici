import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
    private TalonFX intakeMotor;

    public IntakeSubsystem() {
        intakeMotor = new TalonFX(1);
    }

    public void runIntake(double speed) {
        intakeMotor.set(ControlMode.PercentOutput, speed);
    }

    public void stopIntake() {
        intakeMotor.stopMotor();
    }
}

public class IntakeCommand extends CommandBase {
    private final Subsystem intakeSubsystem;
    private final XboxController controller;
    private final int button;

    public IntakeCommand(Subsystem intakeSubsystem, XboxController controller, int button) {
        this.intakeSubsystem = intakeSubsystem;
        this.controller = controller;
        this.button = button;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        double intakeSpeed = controller.getRawButton(button) ? 1.0 : 0.0;
        ((IntakeSubsystem) intakeSubsystem).runIntake(intakeSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        ((IntakeSubsystem) intakeSubsystem).stopIntake();
    }
}

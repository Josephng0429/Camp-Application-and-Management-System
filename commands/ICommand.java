package commands;

import user.User;

public interface ICommand {
	public void execute(User user);

	public void printOption();
}

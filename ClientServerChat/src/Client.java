import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Client extends JFrame {

	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIP;
	private Socket connection;

	// constructor
	public Client(String host) {
		super("Client");
		serverIP = host;
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				sendMessage(event.getActionCommand());
				userText.setText("");
			}
		});
		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow));
		setSize(300, 150); // Sets the window size
		setVisible(true);
	}

	// allows user to type
	private void ableToType(final boolean tof) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				userText.setEditable(tof);
			}
		});
	}

	// Close connection
	private void closeConnection() {
		showMessage("\n Closing the connection!");
		ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// connect to server
	private void connectToServer() throws IOException {
		showMessage("Attempting connection... \n");
		connection = new Socket(InetAddress.getByName(serverIP), 6789);
		showMessage("Connection Established! Connected to: " + connection.getInetAddress().getHostName());
	}

	// send message to server
	private void sendMessage(String message) {
		try {
			output.writeObject("CLIENT - " + message);
			output.flush();
			showMessage("\nCLIENT - " + message);
		} catch (IOException ioException) {
			chatWindow.append("\n Oops! Something went wrong!");
		}
	}

	// set up streams
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n The streams are now set up! \n");
	}

	// update chat window
	private void showMessage(final String message) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				chatWindow.append(message);
			}
		});
	}

	// connect to server
	public void startRunning() {
		try {
			connectToServer();
			setupStreams();
			whileChatting();
		} catch (EOFException eofException) {
			showMessage("\n Client terminated the connection");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	// while chatting with server
	private void whileChatting() throws IOException {
		ableToType(true);
		do {
			try {
				message = (String) input.readObject();
				showMessage("\n" + message);
			} catch (ClassNotFoundException classNotFoundException) {
				showMessage("Unknown data received!");
			}
		} while (!message.equals("SERVER - END"));
	}
}
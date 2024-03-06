package com.voongc;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.voongc.rpc.RPCRemoteService;
import com.voongc.rpc.RPCRemoteServiceAsync;
import com.voongc.rpc.RPCUtil;
import com.voongc.service.FieldVerifier;
import com.voongc.service.GreetingResponse;
import com.voongc.service.GreetingService;
import com.voongc.service.GreetingServiceAsync;

import java.util.HashMap;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */

	/**
	 * This is the entry point method.
	 */
	private final TextBox nameField = new TextBox();

	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		//final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");

//				Map<String, Object> params = new HashMap<>();
//				params.put("username", textToServer);
//				params.put("password", "CONST_PASSWORD");
//				sendRequestByRPC(params);
				sendHttpRequestByGWT(getFormJsonData());
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}

	private void sendRequestByRPC(Map<String, Object> params) {
		RPCUtil.createRemoteService().execute(params, new AsyncCallback<Map<String, Object>>() {
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught.getMessage());
			}

			@Override
			public void onSuccess(Map<String, Object> result) {
				String message = result.get("message").toString();
				System.out.println(message);
			}
		});
	}

	private String getFormJsonData() {
		Map<String, Object> params = getFormData();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", new JSONString(params.get("username").toString()));
		jsonObject.put("password", new JSONString(params.get("password").toString()));
		return jsonObject.toString();
	}

	private Map<String, Object> getFormData() {
		//String username = usernameItem.getValueAsString();
		//String password = passwordItem.getValueAsString();
		String username = "admin";
		String password = "123";

		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("password", password);
		return params;
	}

	private void sendHttpRequestByGWT(String json) {
		String actionUrl = GWT.getHostPageBaseURL() + "login";

		// GWT Http
		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, actionUrl);
		requestBuilder.setHeader("Content-Type", "application/json");
		try {
			requestBuilder.sendRequest(json, new RequestCallback() {
				@Override
				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						nameField.setText(response.getText());
						System.out.println("login success: " + response.getText());
					} else {
						System.out.println("login error, status code is: " + response.getStatusCode());
					}
				}

				@Override
				public void onError(Request request, Throwable exception) {
					System.out.println("login error: " + exception.getMessage());
				}
			});
		} catch (RequestException ex) {
			System.out.println(ex.getMessage());
		}
	}
}

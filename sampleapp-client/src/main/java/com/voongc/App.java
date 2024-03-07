package com.voongc;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.voongc.DTO.CoffeeDto;
import com.voongc.rpc.RPCUtil;
import com.voongc.service.FieldVerifier;

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
	 * This is the entry point method.
	 */
	private TextBox gradeField;
	private TextBox typeFiled;
	private TextBox sizeField;
	private TextBox sugarField;

	private GwtCoffeeService gwtCoffeeService = GWT.create(GwtCoffeeService.class);

	public void onModuleLoad() {
		gradeField = new TextBox();
		typeFiled = new TextBox();
		sizeField = new TextBox();
		sugarField = new TextBox();

		final Button sendButton = new Button("Send");
		//final TextBox nameField = new TextBox();
		gradeField.setText("GRADE");
		typeFiled.setText("TYPE");
		sizeField.setText("0.4");
		sugarField.setText("2");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		RootPanel.get("gradeFieldContainer").add(gradeField);
		RootPanel.get("typeFieldContainer").add(typeFiled);
		RootPanel.get("sizeFieldContainer").add(sizeField);
		RootPanel.get("sugarFieldContainer").add(sugarField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

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
				makeCoffeeAndGetResult();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					makeCoffeeAndGetResult();
				}
			}

			private void makeCoffeeAndGetResult() {
				sendButton.setEnabled(false);

				gwtCoffeeService.makeCoffee(typeFiled.getText(), gradeField.getText(), sizeField.getText(), Integer.parseInt(sugarField.getText()), new GwtCoffeeService.CoffeeCallback() {
					@Override
					public void onSuccess(CoffeeDto coffeeDto) {
						typeFiled.setText(coffeeDto.type);
						gradeField.setText(coffeeDto.grade);
						sizeField.setText(coffeeDto.size);
						sugarField.setText(String.valueOf(coffeeDto.sugar));
						sendButton.setEnabled(true);
					}

					@Override
					public void onFailure(Throwable throwable) {
						typeFiled.setText("FAILURE");
						gradeField.setText("FAILURE");
						sizeField.setText("FAILURE");
						sugarField.setText("FAILURE");
						sendButton.setEnabled(true);
					}
				});


			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		typeFiled.addKeyUpHandler(handler);
		gradeField.addKeyUpHandler(handler);
		sizeField.addKeyUpHandler(handler);
		sugarField.addKeyUpHandler(handler);
	}

}

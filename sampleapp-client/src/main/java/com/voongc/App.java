package com.voongc;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.*;
import com.voongc.DTO.*;

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
	private TextBox cupSizeField;
	private TextBox cupBalanceField;
	private TextBox typeNameField;
	private TextBox gradeNameField;
	private TextBox gradeRoastField;
	private TextBox gradeBalanceField;
	private TextBox goodNameField;
	private TextBox goodBalanceField;
	private ListBox removeTo;
	private TextBox removeToText;
	private ListBox refillTo;
	private TextBox refillText;
	private TextBox refillAmount;

	private GwtCoffeeService gwtCoffeeService = GWT.create(GwtCoffeeService.class);

	public void onModuleLoad() {
		prepareMakeCoffeePanel();
		prepareAddCupPanel();
		prepareAddTypePanel();
		prepareAddGradePanel();
		prepareAddGoodPanel();
		prepareRefillPanel();
		prepareRemovePanel();
	}

	public void prepareMakeCoffeePanel(){
		gradeField = new TextBox();
		typeFiled = new TextBox();
		sizeField = new TextBox();
		sugarField = new TextBox();

		final Button makeButton = new Button("Make");
		gradeField.setText("GRADE");
		typeFiled.setText("TYPE");
		sizeField.setText("0.4");
		sugarField.setText("2");
		makeButton.addStyleName("sendButton");

		RootPanel.get("gradeFieldContainer").add(gradeField);
		RootPanel.get("typeFieldContainer").add(typeFiled);
		RootPanel.get("sizeFieldContainer").add(sizeField);
		RootPanel.get("sugarFieldContainer").add(sugarField);
		RootPanel.get("sendButtonContainer").add(makeButton);

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

			}

			private void makeCoffeeAndGetResult() {
				makeButton.setEnabled(false);

				gwtCoffeeService.makeCoffee(typeFiled.getText(), gradeField.getText(), sizeField.getText(), Integer.parseInt(sugarField.getText()), new GwtCoffeeService.CoffeeCallback() {
					@Override
					public void onSuccess(CoffeeDto coffeeDto) {
						typeFiled.setText(coffeeDto.type);
						gradeField.setText(coffeeDto.grade);
						sizeField.setText(coffeeDto.size);
						sugarField.setText(String.valueOf(coffeeDto.sugar));
						makeButton.setEnabled(true);
					}

					@Override
					public void onFailure(Throwable throwable) {
						typeFiled.setText("FAILURE");
						gradeField.setText("FAILURE");
						sizeField.setText("FAILURE");
						sugarField.setText("FAILURE");
						makeButton.setEnabled(true);
					}
				});


			}
		}
		MyHandler handler = new MyHandler();
		makeButton.addClickHandler(handler);
	}

	public void prepareAddCupPanel(){
		cupSizeField = new TextBox();
		cupBalanceField = new TextBox();

		final Button addCupButton = new Button("AddCup");

		addCupButton.addStyleName("sendButton");

		RootPanel.get("cupSizeFieldContainer").add(cupSizeField);
		RootPanel.get("cupBalanceFieldContainer").add(cupBalanceField);
		RootPanel.get("addCupButtonContainer").add(addCupButton);

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				addCup();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
			}

			private void addCup(){
				addCupButton.setEnabled(false);

				gwtCoffeeService.addCup(new CupDto(Float.parseFloat(cupSizeField.getText()), Integer.parseInt(cupBalanceField.getText())), new GwtCoffeeService.AddCallback() {
					@Override
					public void onSuccess() {
						cupSizeField.setText("");
						cupBalanceField.setText("");
						addCupButton.setEnabled(true);
					}

					@Override
					public void onFailure(Throwable throwable) {
						addCupButton.setEnabled(true);
					}
				});


			}
		}
		MyHandler handler = new MyHandler();
		addCupButton.addClickHandler(handler);
	}

	public void prepareAddTypePanel(){
		typeNameField = new TextBox();
		final Button addTypeButton = new Button("AddType");

		addTypeButton.addStyleName("sendButton");

		RootPanel.get("typeNameFieldContainer").add(typeNameField);
		RootPanel.get("addTypeButtonContainer").add(addTypeButton);

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				addType();
			}

			/**
			 * Fired when the user types in the nameField.
			 */

			private void addType(){
				addTypeButton.setEnabled(false);

				gwtCoffeeService.addType(new TypeDto(typeNameField.getText()), new GwtCoffeeService.AddCallback() {
					@Override
					public void onSuccess() {
						typeNameField.setText("");
						addTypeButton.setEnabled(true);
					}

					@Override
					public void onFailure(Throwable throwable) {
						addTypeButton.setEnabled(true);
					}
				});


			}
			@Override
			public void onKeyUp(KeyUpEvent keyUpEvent) {
			}
		}
		MyHandler handler = new MyHandler();
		addTypeButton.addClickHandler(handler);
	}

	public void prepareAddGradePanel(){
		gradeNameField = new TextBox();
		gradeBalanceField = new TextBox();
		gradeRoastField = new TextBox();
		final Button addGradeButton = new Button("AddGrade");
		addGradeButton.addStyleName("sendButton");

		RootPanel.get("gradeNameFieldContainer").add(gradeNameField);
		RootPanel.get("gradeBalanceFieldContainer").add(gradeBalanceField);
		RootPanel.get("gradeRoastFieldContainer").add(gradeRoastField);
		RootPanel.get("addGradeButtonContainer").add(addGradeButton);

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				addGrade();
			}

			/**
			 * Fired when the user types in the nameField.
			 */

			private void addGrade() {
				addGradeButton.setEnabled(false);
				gwtCoffeeService.addGrade(new GradeDto(gradeNameField.getText(),
						Integer.parseInt(gradeRoastField.getText()),
						Integer.parseInt(gradeBalanceField.getText())), new GwtCoffeeService.AddCallback() {
					@Override
					public void onSuccess() {
						gradeNameField.setText("");
						gradeRoastField.setText("");
						gradeBalanceField.setText("");
						addGradeButton.setEnabled(true);
					}

					@Override
					public void onFailure(Throwable throwable) {
						addGradeButton.setEnabled(true);
					}
				});


			}
			@Override
			public void onKeyUp(KeyUpEvent keyUpEvent) {
			}
		}
		MyHandler handler = new MyHandler();
		addGradeButton.addClickHandler(handler);
	}

	public void prepareAddGoodPanel(){
		goodNameField = new TextBox();
		goodBalanceField = new TextBox();
		final Button addGoodButton = new Button("AddGood");
		addGoodButton.addStyleName("sendButton");

		RootPanel.get("goodNameFieldContainer").add(goodNameField);
		RootPanel.get("goodBalanceFieldContainer").add(goodBalanceField);
		RootPanel.get("addGoodButtonContainer").add(addGoodButton);

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				addGood();
			}

			/**
			 * Fired when the user types in the nameField.
			 */

			private void addGood() {
				addGoodButton.setEnabled(false);
				gwtCoffeeService.addGood(new GoodDto(goodNameField.getText(), Integer.parseInt(goodBalanceField.getText())), new GwtCoffeeService.AddCallback() {
					@Override
					public void onSuccess() {
						goodNameField.setText("");
						goodBalanceField.setText("");
						addGoodButton.setEnabled(true);
					}

					@Override
					public void onFailure(Throwable throwable) {
						addGoodButton.setEnabled(true);
					}
				});


			}
			@Override
			public void onKeyUp(KeyUpEvent keyUpEvent) {
			}
		}
		MyHandler handler = new MyHandler();
		addGoodButton.addClickHandler(handler);
	}

	public void prepareRefillPanel(){

		refillTo = new ListBox();
		refillTo.addItem("type");
		refillTo.addItem("grade");
		refillTo.addItem("good");
		refillTo.addItem("cup");
		refillTo.setSelectedIndex(0);

		refillText = new TextBox();
		final Button refillButton = new Button("Refill");
		refillButton.addStyleName("sendButton");

		refillAmount = new TextBox();

		RootPanel.get("refillToContainer").add(refillTo);
		RootPanel.get("refillToTextFieldContainer").add(refillText);
		RootPanel.get("refillAmountContainer").add(refillAmount);
		RootPanel.get("refillButtonContainer").add(refillButton);

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				refill();
			}

			/**
			 * Fired when the user types in the nameField.
			 */

			private void refill() {
				refillButton.setEnabled(false);
				gwtCoffeeService.refill(refillTo.getSelectedItemText(), refillText.getText(), Integer.parseInt(refillAmount.getText()), new GwtCoffeeService.RefillCallback() {

					@Override
					public void onSuccess(JSONObject jsonValue) {
						refillText.setText("");
						refillButton.setEnabled(true);
					}

					@Override
					public void onFailure(Throwable throwable) {
						refillButton.setEnabled(true);
					}
				});


			}
			@Override
			public void onKeyUp(KeyUpEvent keyUpEvent) {
			}
		}
		MyHandler handler = new MyHandler();
		refillButton.addClickHandler(handler);
	}

	public void prepareRemovePanel(){
		removeTo = new ListBox();
		removeTo.addItem("type");
		removeTo.addItem("grade");
		removeTo.addItem("good");
		removeTo.addItem("cup");
		removeTo.setSelectedIndex(0);

		removeToText = new TextBox();
		final Button removeButton = new Button("Remove");
		removeButton.addStyleName("sendButton");

		RootPanel.get("removeToContainer").add(removeTo);
		RootPanel.get("removeToTextFieldContainer").add(removeToText);
		RootPanel.get("removeButtonContainer").add(removeButton);

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				remove();
			}

			/**
			 * Fired when the user types in the nameField.
			 */

			private void remove() {
				removeButton.setEnabled(false);
				gwtCoffeeService.remove(removeTo.getSelectedItemText(), removeToText.getText(), new GwtCoffeeService.RemoveCallback() {

					@Override
					public void onSuccess(JSONObject jsonValue) {
						removeToText.setText("");
						removeButton.setEnabled(true);
					}

					@Override
					public void onFailure(Throwable throwable) {
						removeButton.setEnabled(true);
					}
				});


			}
			@Override
			public void onKeyUp(KeyUpEvent keyUpEvent) {
			}
		}
		MyHandler handler = new MyHandler();
		removeButton.addClickHandler(handler);
	}

}

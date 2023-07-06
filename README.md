# androidDevControl

The app serves as a front-end to a system designed to control physical hardware.
Hardware refers to devices such as Raspberry Pi, ESP32, or Arduino that have internet connectivity (HTTP and WS).

![System diagram](screenshots/devC_diagram.jpg)

In this diagram, each user has the ability to control their own devices as the device administrator. Additionally, users can control specific parts of other devices if they have been granted the necessary permissions by the respective device administrator.

The device itself is designed to incorporate its own input/output (IO) capabilities through electronic components such as LEDs, temperature sensors, light sensors, relays, and more.
Values from these sensors or actuators are then transmitted to or received from the server.

These "values" can be controlled from an Android device using one of the available UI controls.

Numeric field control:
The numeric field control is defined with properties such as minimal value, maximal value, and value step.
This control is specifically designed to allow users to adjust the brightness of a LED strip.

![Numeric field](screenshots/devC_numeric.jpg)

Text field control:
The text field control allows the user to send or receive a string to or from a device.
When the „Change“ button is clicked, a dialog will appear.

![Text field](screenshots/devC_text.jpg)

![Text field dialog](screenshots/devC_text_dialog.jpg)

Boolean field control:
The button field control allows the user to send or receive a boolean value to or from a device in an intuitive button-like format.

![Boolean field](screenshots/devC_boolean.jpg)

Multiple choice field control:
The multiple-choice field allows the user to select a value from a list of options presented in an aesthetically pleasing dialog form.

![Multiple choice field](screenshots/devC_multiple_choice.jpg)

![Multiple choice field dialog](screenshots/devC_multiple_choice_dialog.jpg)

RGB field control:

The RGB field control enables the user to send or receive an RGB value to or from a device through an attractive RGB dialog form.
This control is specifically designed for setting colors for an RGB LED strip.

![RGB field](screenshots/devC_rgb.jpg)

![RGB field dialog](screenshots/devC_rgb_dialog.jpg)

These controls can be organised in groups and complex groups.
A group is essentially an array of fields with a name.

![Group](screenshots/devC_group.jpg)

On the other hand, a complex group introduces the concept of states, which enables the user to control a single input/output (I/O) in different ways, as illustrated in the image below.

![Complex Group](screenshots/devC_complex_group.jpg)

![Complex group dialog](screenshots/devC_complex_group_dialog.jpg)

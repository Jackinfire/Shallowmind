import tkinter as tk
from tkinter import ttk
from PIL import Image, ImageTk
from datetime import datetime
import random

# Initialize the main window
root = tk.Tk()
root.title("Patient Monitoring Dashboard")
root.geometry("1920x1080")
root.configure(bg="#AABBCC")  # Set the root window's background color

# Sidebar
sidebar = tk.Frame(root, bg="black", width=200, height=768)
sidebar.pack(side=tk.LEFT, fill=tk.Y)

buttons = ["Overview", "Details", "Options", "Settings"]
for text in buttons:
    btn = tk.Button(
        sidebar,
        text=text,
        bg="white",
        fg="black",
        font=("Arial", 14, "bold"),
        borderwidth=0
    )
    btn.pack(pady=20, padx=20, fill=tk.X)

# Main Dashboard Frame
main_frame = tk.Frame(root, bg="#AABBCC")
main_frame.pack(side=tk.LEFT, fill=tk.BOTH, expand=True, padx=10, pady=10)

# Header
header_frame = tk.Frame(main_frame, bg="#AABBCC")
header_frame.pack(fill=tk.X, pady=10)

# Configure grid columns to center the content and keep time at the top-right
header_frame.grid_columnconfigure(0, weight=1)  # Empty space on the left
header_frame.grid_columnconfigure(1, weight=0)  # Left arrow
header_frame.grid_columnconfigure(2, weight=0)  # Dropdown
header_frame.grid_columnconfigure(3, weight=0)  # Right arrow
header_frame.grid_columnconfigure(4, weight=1)  # Empty space between center and right
header_frame.grid_columnconfigure(5, weight=0)  # Time label (top right)

# Left Arrow Button
def move_left():
    current_index = wards.index(ward_selection_var.get())
    new_index = (current_index - 1) % len(wards)
    ward_selection_var.set(wards[new_index])
    display_patients(wards[new_index])

left_arrow = tk.Button(
    header_frame,
    text="←",
    font=("Arial", 16, "bold"),
    bg="white",
    fg="black",
    command=move_left,
    width=3,  # Adjust width for consistency
    padx=5,   # Add horizontal padding
    pady=5    # Add vertical padding to match the dropdown height
)
left_arrow.grid(row=0, column=1, padx=5)

# Dropdown to select ward
ward_selection_var = tk.StringVar(value="Ward A")
wards = ["Ward A", "Ward B", "Ward C", "Ward D"]
ward_dropdown = ttk.Combobox(
    header_frame,
    textvariable=ward_selection_var,
    values=wards,
    state="readonly",
    width=15
)
# Add consistent style to the dropdown
style = ttk.Style()
style.configure("TCombobox", padding=(10, 5))  # Horizontal and vertical padding
ward_dropdown.grid(row=0, column=2, padx=5)

# Right Arrow Button
def move_right():
    current_index = wards.index(ward_selection_var.get())
    new_index = (current_index + 1) % len(wards)
    ward_selection_var.set(wards[new_index])
    display_patients(wards[new_index])

right_arrow = tk.Button(
    header_frame,
    text="→",
    font=("Arial", 16, "bold"),
    bg="white",
    fg="black",
    command=move_right,
    width=3,  # Adjust width for consistency
    padx=5,   # Add horizontal padding
    pady=5    # Add vertical padding to match the dropdown height
)
right_arrow.grid(row=0, column=3, padx=5)

# Display current time in the top right
time_label = tk.Label(header_frame, text="Fetching time...", font=("Arial", 14), bg="#AABBCC", fg="black")
time_label.grid(row=0, column=5, padx=20, sticky="e")  # Sticky aligns it to the right

# Patient Data
patients_data = {
    "Ward A": [f"Patient {i+1}" for i in range(6)],
    "Ward B": [f"Patient {i+7}" for i in range(6)],
    "Ward C": [f"Patient {i+13}" for i in range(6)],
    "Ward D": [f"Patient {i+19}" for i in range(6)],
}

# Initialize patient data
patient_status = {}
for ward in patients_data:
    patient_status[ward] = {}
    for i, patient in enumerate(patients_data[ward]):
        # All patients start with Good Posture
        patient_status[ward][i] = {
            'posture': 'Good Posture',
            'good_posture_time': 0  # Time in seconds
        }

# Patient Display Grid
grid_frame = tk.Frame(main_frame, bg="#AABBCC")
grid_frame.pack(fill=tk.BOTH, expand=True, pady=20)

# Placeholder icon for patients
try:
    image_path = r"left.png"
    image = Image.open(image_path)
    resized_image = image.resize((100, 100), Image.LANCZOS)
    placeholder_icon = ImageTk.PhotoImage(resized_image)
except Exception as e:
    print(f"Error loading image: {e}")
    placeholder_icon = None

def get_current_time():
    """Fetch the current time from the local device."""
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")

# Store patient labels and buttons for dynamic updates
patient_labels = []
patient_status_buttons = []

def update_header_time():
    """Update the time displayed on the header every second."""
    current_time = get_current_time()
    time_label.config(text=f"Current Time: {current_time}")
    root.after(1000, update_header_time)  # Schedule to run again in 1 second

def update_patient_times():
    """Update the 'Last updated' time for each patient every minute."""
    current_time = get_current_time()
    for label in patient_labels:
        label.config(text=f"Last updated: {current_time}")
    root.after(60000, update_patient_times)  # Schedule to run again in 60 seconds

def update_patient_postures():
    """Update patient postures according to the specified rules."""
    for ward in patient_status:
        for patient_id in patient_status[ward]:
            status = patient_status[ward][patient_id]
            # Only proceed if the patient is in Good Posture
            if status['posture'] == 'Good Posture':
                status['good_posture_time'] += 1  # Increment time by 1 second
                # Every 10 seconds, 50% chance to reset the counter
                if status['good_posture_time'] % 10 == 0:
                    if random.choice([True, False]):
                        status['good_posture_time'] = 0  # Reset the counter
                # If the patient has been in Good Posture for 20 consecutive seconds
                if status['good_posture_time'] >= 20:
                    status['posture'] = 'Bad Posture'
                    status['good_posture_time'] = 0  # Reset the counter
                    # Update the GUI if the patient is displayed
                    if ward == ward_selection_var.get():
                        update_patient_display(patient_id)
            else:
                # Patient is in Bad Posture; no action required in this scenario
                pass
    # Schedule the next update
    root.after(1000, update_patient_postures)  # Update every 1 second

def update_patient_display(patient_id):
    """Update the display for a specific patient."""
    # Find the index of the patient in the displayed patients
    for idx, btn in enumerate(patient_status_buttons):
        if idx == patient_id:
            ward = ward_selection_var.get()
            posture = patient_status[ward][patient_id]['posture']
            btn.config(
                text=posture,
                bg="green" if posture == "Good Posture" else "red"
            )
            break

def display_patients(ward):
    # Clear the grid first
    for widget in grid_frame.winfo_children():
        widget.destroy()

    # Reset patient_labels and status_buttons for the current ward
    patient_labels.clear()
    patient_status_buttons.clear()

    # Get the patients for the selected ward
    patients = patients_data.get(ward, [])

    # Display in a 3x2 grid
    for i, patient in enumerate(patients):
        frame = tk.Frame(grid_frame, bg="white", pady=10, padx=10)
        frame.grid(row=i // 3, column=i % 3, padx=10, pady=10, sticky="nsew")

        # Add placeholder image
        if placeholder_icon:
            img_label = tk.Label(frame, image=placeholder_icon, bg="white")
            img_label.grid(row=0, column=0, pady=10)
        else:
            img_label = tk.Label(frame, text="Image not found", bg="white", fg="black")
            img_label.grid(row=0, column=0, pady=10)

        # Patient details
        tk.Label(frame, text=patient, font=("Arial", 14, "bold"), bg="white", fg="black").grid(row=1, column=0, pady=5)
        tk.Label(frame, text=f"{ward}, Room {204 + i}", font=("Arial", 12), bg="white", fg="black").grid(row=2, column=0, pady=5)

        # Last updated label for each patient
        last_updated_label = tk.Label(frame, text=f"Last updated: {get_current_time()}", font=("Arial", 10), bg="white", fg="black")
        last_updated_label.grid(row=3, column=0, pady=5)

        # Save the label for future updates
        patient_labels.append(last_updated_label)

        # Patient status button (posture)
        posture = patient_status[ward][i]['posture']
        status_button = tk.Button(
            frame,
            text=posture,
            bg="green" if posture == "Good Posture" else "red",
            fg="white",
            font=("Arial", 12, "bold"),
        )
        status_button.grid(row=4, column=0, pady=5)

        # Save the status button for future updates
        patient_status_buttons.append(status_button)

        # Expand columns and rows for better space utilization
        grid_frame.grid_rowconfigure(i // 3, weight=1)
        grid_frame.grid_columnconfigure(i % 3, weight=1)

# Bind dropdown selection to update the grid
def on_ward_selection(event):
    selected_ward = ward_selection_var.get()
    display_patients(selected_ward)

ward_dropdown.bind("<<ComboboxSelected>>", on_ward_selection)

# Display initial patients
display_patients("Ward A")

# Start updating the header time and patient times
update_header_time()
update_patient_times()

# Start updating patient postures
update_patient_postures()

root.mainloop()

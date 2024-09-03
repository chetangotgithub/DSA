import tkinter as tk
import webbrowser
import random
import pandas as pd

class CodingProblemGenerator:
    def __init__(self, master):
        self.master = master
        self.master.title("Coding Problem Generator")

        # Load problems from Excel file
        self.file_path = "coding_problems.xlsx"
        self.problems, self.counts = self.load_problems_from_excel(self.file_path)
        self.generated_questions = set()  # To track generated questions

        self.label = tk.Label(master, text="Click the button to get 5 coding problems:")
        self.label.pack()

        self.button = tk.Button(master, text="Generate Problems", command=self.generate_problems)
        self.button.pack()

        self.result_frame = tk.Frame(master)
        self.result_frame.pack()

    def load_problems_from_excel(self, file_path):
        """Load problems from an Excel file."""
        df = pd.read_excel(file_path)
        # Assuming the first column has the question names, the second has URLs, and the third has counts
        problems = [(row[0], row[1]) for row in df.iloc[:, :2].values]
        counts = {row[0]: row[2] for row in df.values}  # Load counts into a dictionary
        return problems, counts

    def generate_problems(self):
        # Reset generated questions if all have been shown
        if len(self.generated_questions) >= len(self.problems):
            self.generated_questions.clear()

        # Get the least frequent questions first
        available_problems = sorted(self.problems, key=lambda x: self.counts[x[0]])
        available_problems = [problem for problem in available_problems if problem[0] not in self.generated_questions]

        selected_problems = available_problems[:5]

        # Update counts and track generated questions
        for problem in selected_problems:
            self.counts[problem[0]] += 1
            self.generated_questions.add(problem[0])

        # Save updated counts to Excel
        self.save_counts_to_excel(self.file_path)

        # Clear previous labels
        for widget in self.result_frame.winfo_children():
            widget.destroy()

        # Create new labels
        for problem in selected_problems:
            label = tk.Label(self.result_frame, text=problem[0], fg="blue", cursor="hand2")
            label.pack()
            label.bind("<Button-1>", lambda event, url=problem[1]: open_url(url))

    def save_counts_to_excel(self, file_path):
        """Save counts to the Excel file."""
        df = pd.read_excel(file_path)
        # Update the count column based on the counts dictionary
        for index, row in df.iterrows():
            problem_name = row[0]
            if problem_name in self.counts:
                df.at[index, 'Count'] = self.counts[problem_name]
        df.to_excel(file_path, index=False)

def open_url(url):
    webbrowser.open_new(url)

if __name__ == "__main__":
    root = tk.Tk()
    app = CodingProblemGenerator(root)
    root.mainloop()
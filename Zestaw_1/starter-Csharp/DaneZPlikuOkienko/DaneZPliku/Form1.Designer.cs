namespace DaneZPlikuOkienko
{
    partial class DaneZPliku
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.btnWybierzPlikSystemu = new System.Windows.Forms.Button();
            this.tbSciezkaDoPlikuSystemu = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.ofd = new System.Windows.Forms.OpenFileDialog();
            this.tbWynik = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.tbSciezkaDoPlikuZTypami = new System.Windows.Forms.TextBox();
            this.btnWybierzPlikZTypami = new System.Windows.Forms.Button();
            this.tbAtrType = new System.Windows.Forms.TextBox();
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.btnStart = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnWybierzPlikSystemu
            // 
            this.btnWybierzPlikSystemu.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnWybierzPlikSystemu.Location = new System.Drawing.Point(702, 12);
            this.btnWybierzPlikSystemu.Name = "btnWybierzPlikSystemu";
            this.btnWybierzPlikSystemu.Size = new System.Drawing.Size(42, 23);
            this.btnWybierzPlikSystemu.TabIndex = 0;
            this.btnWybierzPlikSystemu.Text = "...";
            this.btnWybierzPlikSystemu.UseVisualStyleBackColor = true;
            this.btnWybierzPlikSystemu.Click += new System.EventHandler(this.btnWybierzPlik_Click);
            // 
            // tbSciezkaDoPlikuSystemu
            // 
            this.tbSciezkaDoPlikuSystemu.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.tbSciezkaDoPlikuSystemu.Location = new System.Drawing.Point(193, 12);
            this.tbSciezkaDoPlikuSystemu.Name = "tbSciezkaDoPlikuSystemu";
            this.tbSciezkaDoPlikuSystemu.ReadOnly = true;
            this.tbSciezkaDoPlikuSystemu.Size = new System.Drawing.Size(503, 22);
            this.tbSciezkaDoPlikuSystemu.TabIndex = 1;
            this.tbSciezkaDoPlikuSystemu.Click += new System.EventHandler(this.btnWybierzPlik_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(166, 17);
            this.label1.TabIndex = 2;
            this.label1.Text = "Ścieżka do pliku systemu";
            // 
            // tbWynik
            // 
            this.tbWynik.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tbWynik.Location = new System.Drawing.Point(0, 0);
            this.tbWynik.Multiline = true;
            this.tbWynik.Name = "tbWynik";
            this.tbWynik.Size = new System.Drawing.Size(364, 309);
            this.tbWynik.TabIndex = 3;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 47);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(166, 17);
            this.label2.TabIndex = 4;
            this.label2.Text = "Ścieżka do pliku z typami";
            // 
            // tbSciezkaDoPlikuZTypami
            // 
            this.tbSciezkaDoPlikuZTypami.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.tbSciezkaDoPlikuZTypami.Location = new System.Drawing.Point(193, 44);
            this.tbSciezkaDoPlikuZTypami.Name = "tbSciezkaDoPlikuZTypami";
            this.tbSciezkaDoPlikuZTypami.ReadOnly = true;
            this.tbSciezkaDoPlikuZTypami.Size = new System.Drawing.Size(503, 22);
            this.tbSciezkaDoPlikuZTypami.TabIndex = 5;
            // 
            // btnWybierzPlikZTypami
            // 
            this.btnWybierzPlikZTypami.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnWybierzPlikZTypami.Location = new System.Drawing.Point(702, 44);
            this.btnWybierzPlikZTypami.Name = "btnWybierzPlikZTypami";
            this.btnWybierzPlikZTypami.Size = new System.Drawing.Size(42, 23);
            this.btnWybierzPlikZTypami.TabIndex = 6;
            this.btnWybierzPlikZTypami.Text = "...";
            this.btnWybierzPlikZTypami.UseVisualStyleBackColor = true;
            this.btnWybierzPlikZTypami.Click += new System.EventHandler(this.btnWybierzPlikZTypami_Click);
            // 
            // tbAtrType
            // 
            this.tbAtrType.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tbAtrType.Location = new System.Drawing.Point(0, 0);
            this.tbAtrType.Multiline = true;
            this.tbAtrType.Name = "tbAtrType";
            this.tbAtrType.Size = new System.Drawing.Size(361, 309);
            this.tbAtrType.TabIndex = 7;
            // 
            // splitContainer1
            // 
            this.splitContainer1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.splitContainer1.Location = new System.Drawing.Point(15, 88);
            this.splitContainer1.Name = "splitContainer1";
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.tbWynik);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.tbAtrType);
            this.splitContainer1.Size = new System.Drawing.Size(729, 309);
            this.splitContainer1.SplitterDistance = 364;
            this.splitContainer1.TabIndex = 8;
            // 
            // btnStart
            // 
            this.btnStart.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.btnStart.Location = new System.Drawing.Point(308, 403);
            this.btnStart.Name = "btnStart";
            this.btnStart.Size = new System.Drawing.Size(139, 43);
            this.btnStart.TabIndex = 9;
            this.btnStart.Text = "Pracuj";
            this.btnStart.UseVisualStyleBackColor = true;
            this.btnStart.Click += new System.EventHandler(this.btnStart_Click);
            // 
            // DaneZPliku
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(756, 458);
            this.Controls.Add(this.btnStart);
            this.Controls.Add(this.btnWybierzPlikZTypami);
            this.Controls.Add(this.tbSciezkaDoPlikuZTypami);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.tbSciezkaDoPlikuSystemu);
            this.Controls.Add(this.btnWybierzPlikSystemu);
            this.Controls.Add(this.splitContainer1);
            this.MinimumSize = new System.Drawing.Size(700, 500);
            this.Name = "DaneZPliku";
            this.Text = "Dane z pliku";
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel1.PerformLayout();
            this.splitContainer1.Panel2.ResumeLayout(false);
            this.splitContainer1.Panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
            this.splitContainer1.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnWybierzPlikSystemu;
        private System.Windows.Forms.TextBox tbSciezkaDoPlikuSystemu;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.OpenFileDialog ofd;
        private System.Windows.Forms.TextBox tbWynik;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox tbSciezkaDoPlikuZTypami;
        private System.Windows.Forms.Button btnWybierzPlikZTypami;
        private System.Windows.Forms.TextBox tbAtrType;
        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.Button btnStart;
    }
}


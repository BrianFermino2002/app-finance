package com.example.finance.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.finance.R
import com.example.finance.databinding.FragmentRelatorioBinding
import com.example.finance.presentation.HomeActivity
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import android.content.Context
import com.example.finance.presentation.ThemeManager.Companion.setCustomizedThemes
import com.example.finance.presentation.ThemeStorage.Companion.getThemeColor
import com.example.finance.presentation.ThemeStorage.Companion.setThemeColor
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.PageSize
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

private const val TAG = "MainActivity"

class FragmentRelatorio: Fragment() {

    lateinit var lanc: List<String>
    private lateinit var binding: FragmentRelatorioBinding
    private val viewModel by activityViewModels<LancamentoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRelatorioBinding.inflate(inflater, container, false)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        observePdf()

        binding.btnTeste.setOnClickListener {

            //gerarPDF(requireContext(), lanc, "Lancamento")
            //gerarExcel(requireContext(), lanc, "Excel")
        }

    }

    fun gerarPDF(context: Context, listaStrings: List<String>, nomeArquivo: String) {
        val document = Document(PageSize.A4)

        try {
            val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            if (!storageDir.exists()) {
                storageDir.mkdirs()
            }

            val pdfFile = File(storageDir, "$nomeArquivo.pdf")

            PdfWriter.getInstance(document, FileOutputStream(pdfFile))
            document.open()

            // Adicionar conteúdo ao documento (lista de strings)
            listaStrings.forEach { str ->
                val paragraph = Paragraph(str)
                document.add(paragraph)
            }

            document.close()

            Toast.makeText(context, "PDF gerado com sucesso! Caminho: ${pdfFile.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Erro ao gerar PDF", Toast.LENGTH_SHORT).show()
        }
    }

    fun gerarExcel(context: Context, listaStrings: List<String>, nomeArquivo: String) {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Planilha 1")

        try {
            listaStrings.forEachIndexed { rowIndex, str ->
                val row = sheet.createRow(rowIndex)
                val cell = row.createCell(0)
                cell.setCellValue(str)
            }

            val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            if (!storageDir.exists()) {
                storageDir.mkdirs()
            }

            val excelFile = File(storageDir, "$nomeArquivo.xlsx")

            val fileOut = FileOutputStream(excelFile)
            workbook.write(fileOut)
            fileOut.close()

            Toast.makeText(context, "Excel gerado com sucesso! Caminho: ${excelFile.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Erro ao gerar Excel", Toast.LENGTH_SHORT).show()
        }
    }


    private fun observePdf(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.lancamentos.collect { lancamentos ->
                lanc = lancamentos.map{
                    it.valor.toString() + " " + it.tipoMov
                }
            }
        }
    }

    private fun observe() {

        lifecycleScope.launchWhenStarted {
            viewModel.getLancamentos(HomeActivity.id.toInt()).collect { state ->
                when (state) {
                    LancamentoState.Loading -> {
                        // @TODO mostrar loading para o usuario
                    }
                    is LancamentoState.Error -> {
                        // @TODO Mostrar error parar o usuario
                    }
                    is LancamentoState.Success -> {
                        lanc = state.lancamento.map{
                            it.valor.toString() + " " + it.tipoMov
                        }
                    }
                }
            }
        }
    }


}
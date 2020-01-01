package com.example.raportybhp.Report

import com.itextpdf.text.*
import com.itextpdf.text.pdf.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
class PositionContentInCell {
    enum class POSITION {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

    internal inner class ImageEvent(protected var img: Image) : PdfPCellEvent {
        override fun cellLayout(
            cell: PdfPCell,
            position: Rectangle,
            canvases: Array<PdfContentByte>
        ) {
            img.scaleToFit(position.width, position.height)
            img.setAbsolutePosition(
                position.left + (position.width - img.scaledWidth) / 2,
                position.bottom + (position.height - img.scaledHeight) / 2
            )
            val canvas = canvases[PdfPTable.BACKGROUNDCANVAS]
            try {
                canvas.addImage(img)
            } catch (ex: DocumentException) { // do nothing
            }
        }

    }

    internal inner class PositionEvent(protected var content: Phrase, protected var pos: POSITION) :
        PdfPCellEvent {
        override fun cellLayout(
            cell: PdfPCell,
            position: Rectangle,
            canvases: Array<PdfContentByte>
        ) {
            val canvas = canvases[PdfPTable.TEXTCANVAS]
            var x = 0f
            var y = 0f
            var alignment = 0
            when (pos) {
                POSITION.TOP_LEFT -> {
                    x = position.getLeft(3f)
                    y = position.getTop(content.leading)
                    alignment = Element.ALIGN_LEFT
                }
                POSITION.TOP_RIGHT -> {
                    x = position.getRight(3f)
                    y = position.getTop(content.leading)
                    alignment = Element.ALIGN_RIGHT
                }
                POSITION.BOTTOM_LEFT -> {
                    x = position.getLeft(3f)
                    y = position.getBottom(3f)
                    alignment = Element.ALIGN_LEFT
                }
                POSITION.BOTTOM_RIGHT -> {
                    x = position.getRight(3f)
                    y = position.getBottom(3f)
                    alignment = Element.ALIGN_RIGHT
                }
            }
            ColumnText.showTextAligned(canvas, alignment, content, x, y, 0f)
        }

    }

    @Throws(IOException::class, DocumentException::class)
    fun createPdf(dest: String?) { // 1. Create a Document which contains a table:
        val document = Document()
        PdfWriter.getInstance(document, FileOutputStream(dest))
        document.open()
        val table = PdfPTable(2)
        val cell1 = PdfPCell()
        val cell2 = PdfPCell()
        val cell3 = PdfPCell()
        val cell4 = PdfPCell()
        // 2. Inside that table, make each cell with specific height:
        cell1.fixedHeight = 50f
        cell2.fixedHeight = 50f
        cell3.fixedHeight = 50f
        cell4.fixedHeight = 50f
        // 3. Each cell has the same background image
        val imgEvent =
            ImageEvent(Image.getInstance(IMG))
        cell1.cellEvent = imgEvent
        cell2.cellEvent = imgEvent
        cell3.cellEvent = imgEvent
        cell4.cellEvent = imgEvent
        // 4. Add text in front of the image at specific position
        cell1.cellEvent = PositionEvent(Phrase("Top left"), POSITION.TOP_LEFT)
        cell2.cellEvent = PositionEvent(Phrase("Top right"), POSITION.TOP_RIGHT)
        cell3.cellEvent = PositionEvent(Phrase("Bottom left"), POSITION.BOTTOM_LEFT)
        cell4.cellEvent = PositionEvent(Phrase("Bottom right"), POSITION.BOTTOM_RIGHT)
        // Wrap it all up!
        table.addCell(cell1)
        table.addCell(cell2)
        table.addCell(cell3)
        table.addCell(cell4)
        document.add(table)
        document.close()
    }

    companion object {
        const val DEST = "results/tables/position_content_in_cell.pdf"
        const val IMG = "res/image/info.png"
        @Throws(IOException::class, DocumentException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val file = File(DEST)
            file.parentFile.mkdirs()
            PositionContentInCell().createPdf(DEST)
        }
    }
}
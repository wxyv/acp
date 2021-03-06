package pers.acp.ftp.conf

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamImplicit
import pers.acp.core.base.BaseXml
import pers.acp.core.log.LogFactory

/**
 * @author zhang by 12/07/2019
 * @since JDK 11
 */
@XStreamAlias("sftp-config")
class SftpConfig : BaseXml() {

    @XStreamImplicit(itemFieldName = "listen")
    var listens: List<SftpListener>? = null

    companion object {

        private val log = LogFactory.getInstance(SftpConfig::class.java)

        @JvmStatic
        fun getInstance(): SftpConfig? =
                try {
                    Load(SftpConfig::class.java) as SftpConfig
                } catch (e: Exception) {
                    log.error(e.message, e)
                    null
                }
    }

}
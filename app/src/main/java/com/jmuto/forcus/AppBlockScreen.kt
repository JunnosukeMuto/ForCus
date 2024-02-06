package com.jmuto.forcus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmuto.forcus.ui.theme.ForCusTheme

@Composable
fun AppBlockScreen() {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 32.dp, bottom = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.icon), contentDescription = null, modifier = Modifier.size(48.dp))
            Text(text = stringResource(id = R.string.app_name), fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
        Text(stringResource(R.string.was_blocked), fontSize = 56.sp, fontWeight = FontWeight.Bold)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    (context as AppBlockService).closeOverlay()
                }
            ) {
                Text(stringResource(R.string.close), fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}
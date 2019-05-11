<?php if(count($response)>0):?>
<div class="errors">
	<?php foreach ($response as $response):?>
		<p style="text-align: center;color: white; z-index: 1;"><?php echo $response; ?></p>
	<?php endforeach?> 
</div>
<?php endif ?>
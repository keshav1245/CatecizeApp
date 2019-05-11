<?php if(count($response)>0):?>
<div class="errors">
	<?php foreach ($response as $response):?>
		<p><?php echo $response; ?></p>
	<?php endforeach?> 
</div>
<?php endif ?>